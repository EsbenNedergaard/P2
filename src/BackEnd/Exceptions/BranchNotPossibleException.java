package BackEnd.Exceptions;

public class BranchNotPossibleException extends Exception {
    Exception reasonWhyBranchNotPossible;

    public BranchNotPossibleException(Exception reasonWhyBranchNotPossible) {
        this.reasonWhyBranchNotPossible = reasonWhyBranchNotPossible;
    }

    public Exception getReasonWhyBranchNotPossible() {
        return reasonWhyBranchNotPossible;
    }
}
