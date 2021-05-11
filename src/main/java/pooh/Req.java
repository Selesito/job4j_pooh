package pooh;

public class Req {
    private static int id;
    private final String method;
    private final String mode;
    private final String text;
    private final String theme;

    private Req(String method, String mode, String text, String theme) {
        this.method = method;
        this.mode = mode;
        this.text = text;
        this.theme = theme;
    }

    public static Req of(String content) {
        String[] parameters = content.split(" ");
        if (parameters.length != 2) {
            throw new IllegalArgumentException();
        }
        String[] themeMode = parameters[1].split("/");
        if (themeMode.length != 2) {
            throw new IllegalArgumentException();
        }
        int id = 0;
        if (themeMode.length >= 3) {
            id = Integer.parseInt(themeMode[2]);
        }
        return new Req(parameters[0], themeMode[0], content, themeMode[1]);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String text() {
        return text;
    }

    public String getTheme() {
        return theme;
    }

    public int getId() {
        return id;
    }
}