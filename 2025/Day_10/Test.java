package Day_10;

import com.microsoft.z3.*;

public class Test{
    public static void main(String[] args) {
        Context ctx = new Context();

        Solver s = ctx.mkSolver();
        IntExpr x = ctx.mkIntConst("x");
        s.add(ctx.mkGt(x, ctx.mkInt(10)));

        System.out.println("Check: " + s.check());
        System.out.println("Model: " + s.getModel());
    }
}