import java.util.HashMap;
import java.util.Map;

public class Eval {

  static Map<String, Integer> idMap = new HashMap<>();

  public static class EvalStm implements Stm.Visitor<Void> {

    public Void visit(Stm.CompoundStm s) {
      s.stm1.accept(this);
      s.stm2.accept(this);
      return null;
    }

    public Void visit(Stm.AssignStm s) {
      idMap.put(s.id, s.exp.accept(new EvalExp()));
      return null;
    }

    public Void visit(Stm.PrintStm s) {
      s.exps.forEach(exp -> System.out.println(exp.accept(new EvalExp())));
      return null;
    }
  }

  public static class EvalExp implements Exp.Visitor<Integer> {

    public Integer visit(Exp.IdExp e) {
      return idMap.get(e.id);
    }

    public Integer visit(Exp.NumExp e) {
      return e.num;
    }

    public Integer visit(Exp.OpExp e) {
      Integer result = null;
      switch(e.op){
        case PLUS:
          result = e.left.accept(this) + e.right.accept(this);
          break;
        case MINUS:
          result = e.left.accept(this) - e.right.accept(this);
          break;
        case TIMES:
          result = e.left.accept(this) * e.right.accept(this);
          break;
        case DIVIDE:
          result = e.left.accept(this) / e.right.accept(this);
          break;
      }
      return result;
    }

    public Integer visit(Exp.EseqExp e) {
      e.stm.accept(new EvalStm());

      return e.exp.accept(this);
    }
  }
}
