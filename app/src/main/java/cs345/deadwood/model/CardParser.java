package cs345.deadwood.model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class CardParser extends GameDataParser {

    public CardParser() {
        super("cards.xml");
    }

    /**
     * Parse cards.xml to create a list of Card objects that implement the ICard interface.
     *
     * @return List of cards in cards.xml
     */
    public List<ICard> getCards() {

        Element root = getRootNode();
        List<ICard> cardList = new ArrayList<>();
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node card = children.item(i);
            if (isNodeEmpty(card)) continue;
            String name = getAttributeNameAsString(card, "name");
            String image = getAttributeNameAsString(card, "img");
            int budget = getAttributeNameAsInt(card, "budget");
            int sceneNumber = 0;
            String sceneDescription = null;
            List<IRole> roleList = new ArrayList<>();
            NodeList cardChildren = card.getChildNodes();
            for (int j = 0; j < cardChildren.getLength(); j++) {
                Node sub = cardChildren.item(j);
                if (isNodeEmpty(sub)) continue;
                switch (sub.getNodeName()) {
                    case "scene":
                        sceneNumber = getAttributeNameAsInt(sub, "number");
                        sceneDescription = sub.getTextContent();
                        break;
                    case "part":
                        roleList.add(new Role(getAttributeNameAsString(sub, "name"),
                                getChildNode(sub, "line").getTextContent(),
                                getAttributeNameAsInt(sub, "level"),
                                parseArea(getChildNode(sub, "area"))));
                        break;
                    default:
                        break;
                }
            }
            ICard c = new Card(name, image, budget, sceneNumber, sceneDescription, roleList);
            cardList.add(c);
        }
        return cardList;
    }


}
