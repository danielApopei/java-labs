package domain;

public class Genre {
    public String genreName;
    public Genre(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreName='" + genreName + '\'' +
                '}';
    }
}
