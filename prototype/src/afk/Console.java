package afk;

public class Console {

    public Console() {

    }

    public String readString() {
        return "";
    }

    public String readAccount(String prompt) {
        print(prompt + " #");
        throw new NumberFormatException();
    }

    public int readAmount(String prompt) {
        print(prompt + " ¢");
        throw new NumberFormatException();
    }

    public void print(String s) {

    }

    public void println(String s) {

    }

}
