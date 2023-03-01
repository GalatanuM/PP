//import libraria principala polyglot din graalvm

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.util.Arrays;

//clasa principala - aplicatie JAVA
class Polyglot {
    private static int[] Random20Python()
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("python",
                "import random\n" +
                        "[random.randint(0, 100) for _ in range(20)]");
        int[] lista = result.as(int[].class);
        polyglot.close();
        return lista;
    }

    private static void afisareJava (int[] lista)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        polyglot.eval("js", "console.log(" + Arrays.toString(lista) + ")");
        polyglot.close();
    }

    private static double medieR(int[] lista) {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        polyglot.eval("R", "vector <- as.numeric(c("+Arrays.toString(lista).replaceAll("[\\[\\]]","")+"))\n" +
                "vector_sortat <- sort(vector)\n" +
                "n <- length(vector_sortat)\n" +
                "delete <- round(n*0.2)\n" +
                "i <- delete+1\n" +
                "j <- n-delete\n" +
                "vector_nou <- vector_sortat[i:j]\n" +
                "medie <- mean(vector_nou)");
        Value medieValue = polyglot.getBindings("R").getMember("medie");
        return medieValue.asDouble();
    }


    //functia MAIN
    public static void main(String[] args) {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        int[] lista = Random20Python();
        afisareJava(lista);
        double medie = medieR(lista);
        System.out.println(medie);
        polyglot.close();
    }
}
