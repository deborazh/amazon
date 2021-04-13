package enums.amazon;

public enum Department {

    MOVIES_AND_TV("Movies & TV"),
    MOVIES_AND_FILMS("Movies & Films"),
    MOVIES("Movies"),
    TOYS_AND_GAMES("Toys & Games"),
    NOVELTY_CLOTHING_AND_MORE("Novelty Clothing & More"),
    BOOKS("Books"),
    SCIENCE_FICTION("Science Fiction"),
    MENS_CLOTHING("Men's Clothing"),
    DINING_AND_ENTERTAINING("Dining & Entertaining");

    private String description;

    Department(String description) {
        this.description = description;
    }

    public String getText() {
        return description;
    }
}
