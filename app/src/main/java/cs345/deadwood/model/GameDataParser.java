package cs345.deadwood.model;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class GameDataParser {

    private final Document doc;

    public GameDataParser(String xmlFile) {
        doc = getDocFromFile(xmlFile);
    }

    private Document getDocFromFile(String filename) throws RuntimeException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            URL resource = getClass().getClassLoader().getResource(filename);
            return db.parse(resource.getPath().replace("%20", " "));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Can't read and parse the file " + filename);
        }
    }

    public Element getRootNode() {
        return doc.getDocumentElement();
    }


    protected IArea parseArea(Node sub) {
        return new Area(getAttributeNameAsInt(sub, "x"),
                getAttributeNameAsInt(sub, "y"),
                getAttributeNameAsInt(sub, "h"),
                getAttributeNameAsInt(sub, "w"));
    }

    protected List<IRole> parseRoleList(Node node) {
        List<IRole> roleList = new ArrayList<>();
        NodeList children = node.getChildNodes();
        for (int k = 0; k < children.getLength(); k++) {
            Node subSub = children.item(k);
            if ("part".equals(subSub.getNodeName())) {
                if (isNodeEmpty(subSub)) continue;
                roleList.add(new Role(getAttributeNameAsString(subSub, "name"),
                        getChildNode(subSub, "line").getTextContent(),
                        getAttributeNameAsInt(subSub, "level"),
                        parseArea(getChildNode(subSub, "area"))));
            }
        }
        return roleList;
    }

    protected Node getChildNode(Node node, String name) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node sub = children.item(i);
            if (isNodeEmpty(sub)) continue;
            if (name.equals(sub.getNodeName())) {
                return sub;
            }
        }
        return null;
    }

    protected String getAttributeNameAsString(Node node, String name) {
        return node.getAttributes().getNamedItem(name).getNodeValue();
    }

    protected int getAttributeNameAsInt(Node node, String name) {
        String value = node.getAttributes().getNamedItem(name).getNodeValue();
        return Integer.parseInt(value);
    }


    protected boolean isNodeEmpty(Node node) {
        if (node instanceof Text) {
            return !"".equals(node.getNodeValue());
        }
        return false;
    }
}
