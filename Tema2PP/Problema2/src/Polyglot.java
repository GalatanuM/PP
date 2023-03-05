import org.graalvm.polyglot.*;
import java.io.*;
import java.util.Scanner;

class Polyglot {

    private static void regresieLiniaraR(String text,String filename,String path,String color) throws IOException
    {
        File file = new File(text);
        Scanner input = new Scanner(file);
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        if(path.matches("_"))path="";
        Value data = polyglot.eval("R","library(lattice)\n" +
                "x <-  c(" + input.nextLine() + ")\n" +
                "y <-  c(" + input.nextLine() + ")\n" +
                "model <- lm(y~x)\n" +
                "jpeg(file = '" + path + filename + ".jpeg')\n" +
                "plot(x,y,col = '" + color + "')\n" +
                "abline(model)\n" +
                "dev.off()");
        polyglot.close();
    }

    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        FileWriter file = new FileWriter("dataset.txt");
        String s;
        System.out.print("X de forma (a,b,c,...):");
        s = input.nextLine();
        file.write(s);
        file.write('\n');

        System.out.print("Y de forma (a,b,c,...):");
        s = input.nextLine();
        file.write(s);
        file.close();

        String filename;
        System.out.print("Numele fisierului jpeg: ");
        filename= input.next();

        String path;
        System.out.print("Path (\"_\" pentru folderul curent): ");
        path=input.next();

        String color;
        System.out.print("Culoarea punctelor (numar sau culoare sau hexazecimal): ");
        color = input.next();

        regresieLiniaraR("dataset.txt", filename, path, color);
    }
}