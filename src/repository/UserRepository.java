package repository;

import entity.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import repository.abstraction.IUserRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private final String filePath = "src/data/users.xml";

    public UserRepository() {
        createFile();
    }

    private void createFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("users");
                doc.appendChild(rootElement);
                saveDocumentToFile(doc);
            } catch (ParserConfigurationException | TransformerException e) {
                System.out.println("An error occurred while creating the XML file: " + e.getMessage());
            }
        }
    }

    public void saveUser(User user) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc;
            File file = new File(filePath);
            if (file.exists()) {
                doc = docBuilder.parse(file);
            } else {
                doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("users");
                doc.appendChild(rootElement);
            }

            Element userElement = createUserElement(doc, user);
            doc.getDocumentElement().appendChild(userElement);
            // Transformer for formatting the XML file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));
            saveDocumentToFile(doc);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("An error occurred while saving the user: " + e.getMessage());
        }
    }

    public void deleteUser(String username) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);

            Node userNode = findUserNode(doc, username);
            if (userNode != null) {
                Node parent = userNode.getParentNode();
                parent.removeChild(userNode);
                saveDocumentToFile(doc);
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("An error occurred while deleting the user: " + e.getMessage());
        }
    }

    public void addFollower(String username, String followerName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);

            Node userNode = findUserNode(doc, username);
            if (userNode != null) {
                Element userElement = (Element) userNode;
                Element followersElement = getOrCreateElement(userElement, "followers");
                Element followerElement = doc.createElement("follower");
                followerElement.setTextContent(followerName);
                followersElement.appendChild(followerElement);
                saveDocumentToFile(doc);
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("An error occurred while adding a follower: " + e.getMessage());
        }
    }

    public void addFollowing(String username, String followingName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);

            Node userNode = findUserNode(doc, username);
            if (userNode != null) {
                Element userElement = (Element) userNode;
                Element followingElement = getOrCreateElement(userElement, "following");
                Element followingNameElement = doc.createElement("name");
                followingNameElement.setTextContent(followingName);
                followingElement.appendChild(followingNameElement);
                saveDocumentToFile(doc);
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("An error occurred while adding a following: " + e.getMessage());
        }
    }

    private Node findUserNode(Document doc, String username) {
        NodeList userList = doc.getElementsByTagName("user");
        for (int i = 0; i < userList.getLength(); i++) {
            Node userNode = userList.item(i);
            if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                Element userElement = (Element) userNode;
                String name = userElement.getAttribute("name");
                if (name.equals(username)) {
                    return userNode;
                }
            }
        }
        return null;
    }

    private Element createUserElement(Document doc, User user) {
        Element userElement = doc.createElement("user");
        userElement.setAttribute("name", user.getName());

        Element passwordElement = doc.createElement("password");
        passwordElement.setTextContent(user.getPassword());
        userElement.appendChild(passwordElement);

        Element followersElement = doc.createElement("followers");
        for (String follower : user.getFollowers()) {
            Element followerElement = doc.createElement("follower");
            followerElement.setTextContent(follower);
            followersElement.appendChild(followerElement);
        }
        userElement.appendChild(followersElement);

        Element followingElement = doc.createElement("following");
        for (String following : user.getFollowing()) {
            Element followingNameElement = doc.createElement("name");
            followingNameElement.setTextContent(following);
            followingElement.appendChild(followingNameElement);
        }
        userElement.appendChild(followingElement);

        return userElement;
    }

    private Element getOrCreateElement(Element parent, String elementName) {
        NodeList nodeList = parent.getElementsByTagName(elementName);
        if (nodeList.getLength() > 0) {
            return (Element) nodeList.item(0);
        } else {
            Document ownerDocument = parent.getOwnerDocument();
            Element newElement = ownerDocument.createElement(elementName);
            parent.appendChild(newElement);
            return newElement;
        }
    }

    private void saveDocumentToFile(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src\\data\\users.xml"));
        transformer.transform(source, result);
    }

    public List<String> getFollowers(String username) {
        List<String> followers = new ArrayList<>();

        try {
            Document doc = loadXmlDocument();
            if (doc != null) {
                NodeList userNodes = doc.getElementsByTagName("user");
                for (int i = 0; i < userNodes.getLength(); i++) {
                    Element userElement = (Element) userNodes.item(i);
                    String name = userElement.getAttribute("name");
                    if (name.equals(username)) {
                        NodeList followerNodes = userElement.getElementsByTagName("follower");
                        for (int j = 0; j < followerNodes.getLength(); j++) {
                            Element followerElement = (Element) followerNodes.item(j);
                            String followerName = followerElement.getTextContent();
                            followers.add(followerName);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving followers: " + e.getMessage());
        }

        return followers;
    }

    public List<String> getFollowing(String username) {
        List<String> following = new ArrayList<>();

        try {
            Document doc = loadXmlDocument();
            if (doc != null) {
                NodeList userNodes = doc.getElementsByTagName("user");
                for (int i = 0; i < userNodes.getLength(); i++) {
                    Element userElement = (Element) userNodes.item(i);
                    String name = userElement.getAttribute("name");
                    if (name.equals(username)) {
                        Element followingElement = (Element) userElement.getElementsByTagName("following").item(0);
                        NodeList followingNameNodes = followingElement.getElementsByTagName("name");
                        for (int j = 0; j < followingNameNodes.getLength(); j++) {
                            Element followingNameElement = (Element) followingNameNodes.item(j);
                            String followingName = followingNameElement.getTextContent();
                            following.add(followingName);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving following: " + e.getMessage());
        }

        return following;
    }

    private Document loadXmlDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.parse(filePath);
    }

    public boolean verifyUser(String username, String password) {
        try {
            Document doc = loadXmlDocument();
            if (doc != null) {
                Node userNode = findUserNode(doc, username);
                if (userNode != null) {
                    Element userElement = (Element) userNode;
                    String storedPassword = getPassword(userElement);
                    return storedPassword.equals(password);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while verifying the user: " + e.getMessage());
        }
        return false;
    }

    private String getPassword(Element userElement) {
        NodeList passwordNodes = userElement.getElementsByTagName("password");
        if (passwordNodes.getLength() > 0) {
            Element passwordElement = (Element) passwordNodes.item(0);
            return passwordElement.getTextContent();
        }
        return null;
    }

    public void unfollow(String username, String followingName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);

            Node userNode = findUserNode(doc, username);
            if (userNode != null) {
                Element userElement = (Element) userNode;
                Element followingElement = (Element) userElement.getElementsByTagName("following").item(0);

                NodeList followingNameNodes = followingElement.getElementsByTagName("name");
                for (int i = 0; i < followingNameNodes.getLength(); i++) {
                    Element followingNameElement = (Element) followingNameNodes.item(i);
                    String name = followingNameElement.getTextContent();
                    if (name.equals(followingName)) {
                        followingElement.removeChild(followingNameElement);
                        saveDocumentToFile(doc);
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("An error occurred while unfollowing: " + e.getMessage());
        }
    }

    public List<String> getAllUserNames() {
        List<String> usernames = new ArrayList<>();

        try {
            Document doc = loadXmlDocument();
            if (doc != null) {
                NodeList userNodes = doc.getElementsByTagName("user");
                for (int i = 0; i < userNodes.getLength(); i++) {
                    Element userElement = (Element) userNodes.item(i);
                    String name = userElement.getAttribute("name");
                    usernames.add(name);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving usernames: " + e.getMessage());
        }

        return usernames;
    }

    public void removeFollower(String username, String followerName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);

            Node userNode = findUserNode(doc, username);
            if (userNode != null) {
                Element userElement = (Element) userNode;
                Element followersElement = (Element) userElement.getElementsByTagName("followers").item(0);

                NodeList followerNodes = followersElement.getElementsByTagName("follower");
                for (int i = 0; i < followerNodes.getLength(); i++) {
                    Element followerElement = (Element) followerNodes.item(i);
                    String name = followerElement.getTextContent();
                    if (name.equals(followerName)) {
                        followersElement.removeChild(followerElement);
                        saveDocumentToFile(doc);
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("An error occurred while removing a follower: " + e.getMessage());
        }
    }

}
