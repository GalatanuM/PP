import org.graalvm.polyglot.*;
import java.io.*;

class Polyglot {

    private static int[] citestePython()
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        polyglot.eval("python","\n" +
                "n = input('Nr aruncari:') \n" +
                "n = int(n)\n" +
                "x = input('Nr de pajuri dorit:')\n" +
                "x = int(x)\n");
        int[] numere = new int[2];
        numere[0] = polyglot.getBindings("python").getMember("n").asInt();
        numere[1] = polyglot.getBindings("python").getMember("x").asInt();
        polyglot.close();
        return numere;
    }
    private static double DistributieBinomialaR(int n,int x)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        polyglot.eval("R","\n" +
                "bin=pbinom("+ x +", "+ n +", 0.5)"
        );
        return polyglot.getBindings("R").getMember("bin").asDouble();
    }

    public static void main(String[] args) throws IOException
    {
        int []nr = citestePython();
        int n,x;
        n = nr[0];
        x = nr[1];
        System.out.println("Procentaj conform distrubutiei binomiale: " + DistributieBinomialaR(n,x)*100+"%");
    }
}