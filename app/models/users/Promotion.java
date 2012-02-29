package models.users;

import play.i18n.Messages;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public enum Promotion {

    B1(1), B2(2), B3(3), M1(4), M2(5);

    private int level;

    private Promotion(int level) {
        this.level = level;
    }

    public String getLabel() {
        return super.toString();
    }

    public static Promotion valueOf(int level) {

        Promotion promotion;

        switch (level) {
            case 1:
                promotion = B1;
                break;
            case 2:
                promotion = B2;
                break;
            case 3:
                promotion = B3;
                break;
            case 4:
                promotion = M1;
                break;
            case 5:
                promotion = M2;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return promotion;
    }
}
