import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {

	@Override
	public int compare(Loan o1, Loan o2) {
		return Long.compare(o1.getPublishedMil(), o2.getPublishedMil());
	}
}
