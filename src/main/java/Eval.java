public class Eval {

  public static class EvalStm implements Stm.Visitor<Void> {

    public Void visit(Stm.CompoundStm s) {
      throw new UnsupportedOperationException("Not implemented!");
    }

    public Void visit(Stm.AssignStm s) {
      throw new UnsupportedOperationException("Not implemented!");
    }

    public Void visit(Stm.PrintStm s) {
      throw new UnsupportedOperationException("Not implemented!");
    }
  }

  public static class EvalExp implements Exp.Visitor<Integer> {

    public Integer visit(Exp.IdExp e) {

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

    }
  }
}
