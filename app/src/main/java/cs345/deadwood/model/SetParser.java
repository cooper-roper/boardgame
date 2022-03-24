package cs345.deadwood.model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetParser extends GameDataParser {

    public SetParser() {
        super("board.xml");
    }

    /**
     * Parse board.xml to create a list of set objects that implement the ISet or ISetScene interface.
     *
     * @return List of Sets in board.xml
     */
    public List<ISet> getSets() {

        Element root = getRootNode();

        List<ISet> setList = new ArrayList<>();
        HashMap<String, ISet> setMap = new HashMap<>();

        NodeList rootChildren = root.getChildNodes();

        for (int i = 0; i < rootChildren.getLength(); i++) {
            Node set = rootChildren.item(i);
            if (isNodeEmpty(set)) continue;
            ISet s = null;
            switch (set.getNodeName()) {
                case "set":
                    s = parseSceneSet(set);
                    setMap.put(s.getName(), s);
                    break;
                case "office":
                    s = parseOfficeSet(set);
                    setMap.put("Office", s);
                    break;
                case "trailer":
                    s = parseTrailer(set);
                    setMap.put("Trailer", s);
                    break;
                default:
                    break;
            }
            if (s != null) {
                setList.add(s);
            }
        }

        for (ISet set : setList) {
            for (String n : set.getNeighborNames()) {
                set.addNeighbor(setMap.get(n));
            }
        }

        return setList;
    }

    private ISet parseSceneSet(Node set) {
        String name = getAttributeNameAsString(set, "name");
        List<String> neighborNames = null;
        IArea area = null;
        List<IRole> roleList = null;
        List<IArea> takeList = new ArrayList<>();
        List<IArea> blankAreaList = null;
        NodeList children = set.getChildNodes();
        for (int j = 0; j < children.getLength(); j++) {
            Node sub = children.item(j);
            if (isNodeEmpty(sub)) continue;
            switch (sub.getNodeName()) {
                case "neighbors":
                    neighborNames = parseNeighborNames(sub);
                    break;
                case "area":
                    area = parseArea(sub);
                    break;
                case "parts":
                    roleList = parseRoleList(sub);
                    break;
                case "takes":
                    NodeList subChildren = sub.getChildNodes();
                    for (int k = 0; k < subChildren.getLength(); k++) {
                        Node subSub = subChildren.item(k);
                        if ("take".equals(subSub.getNodeName())) {
                            Take take = new Take((Area) parseArea(getChildNode(subSub, "area")));
                            take.setNumber(getAttributeNameAsInt(subSub, "number"));
                            takeList.add(take);
                        }
                    }
                    break;
                case "blanks":
                    blankAreaList = parseBlankList(sub);
                    break;
                default:
                    break;
            }
        }
        // Sort take list
        List<IArea> sortedTakes = new ArrayList<>();
        for (int i = 0; i < takeList.size(); i++) {
            for (IArea take : takeList) {
                if (take.getNumber() == (i + 1)) {
                    sortedTakes.add(take);
                    break;
                }
            }
        }
        return new SetBuilder()
                .addName(name)
                .addArea(area)
                .addNeighborNames(neighborNames)
                .addBlankAreas(blankAreaList)
                .addTakes(sortedTakes)
                .addRoles(roleList)
                .GetSetScene();
    }

    private ISet parseOfficeSet(Node set) {
        List<IArea> blankAreaList = null;
        IArea area = null;
        List<String> neighborNames = null;
        NodeList children = set.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node sub = children.item(i);
            if (isNodeEmpty(sub)) continue;
            switch (sub.getNodeName()) {
                case "neighbors":
                    neighborNames = parseNeighborNames(sub);
                    break;
                case "area":
                    area = parseArea(sub);
                    break;
                case "blanks":
                    blankAreaList = parseBlankList(sub);
                    break;
                default:
                    System.out.println("Unknown node:" + sub.getNodeName());
                    break;
            }
        }
        return new SetBuilder()
                .addArea(area)
                .addNeighborNames(neighborNames)
                .addBlankAreas(blankAreaList)
                .GetCastingOffice();

    }

    private ISet parseTrailer(Node set) {
        List<IArea> blankAreaList = null;
        IArea area = null;
        List<String> neighborNames = null;
        NodeList children = set.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node sub = children.item(i);
            if (isNodeEmpty(sub)) continue;
            switch (sub.getNodeName()) {
                case "neighbors":
                    neighborNames = parseNeighborNames(sub);
                    break;
                case "area":
                    area = parseArea(sub);
                    break;
                case "blanks":
                    blankAreaList = parseBlankList(sub);
                    break;
                default:
                    System.out.println("Unknown node:" + sub.getNodeName());
                    break;
            }
        }
        return new SetBuilder()
                .addArea(area)
                .addNeighborNames(neighborNames)
                .addBlankAreas(blankAreaList)
                .GetTrailer();
    }

    private List<String> parseNeighborNames(Node sub) {
        List<String> neighborNames = new ArrayList<>();
        NodeList subChildren = sub.getChildNodes();
        for (int k = 0; k < subChildren.getLength(); k++) {
            Node subSub = subChildren.item(k);
            if ("neighbor".equals(subSub.getNodeName())) {
                neighborNames.add(getAttributeNameAsString(subSub, "name"));
            }
        }
        return neighborNames;
    }

    private List<IArea> parseBlankList(Node node) {
        List<IArea> blankAreas = new ArrayList<>();
        NodeList children = node.getChildNodes();
        for (int k = 0; k < children.getLength(); k++) {
            Node subSub = children.item(k);
            if (isNodeEmpty(subSub)) continue;
            if ("blank".equals(subSub.getNodeName())) {
                BlankArea blank = new BlankArea((Area) parseArea(getChildNode(subSub, "area")));
                blank.setNumber(getAttributeNameAsInt(subSub, "number"));
                blankAreas.add(blank);
            }
        }
        return blankAreas;
    }


}
