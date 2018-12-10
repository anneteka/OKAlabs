package comparable;
import java.util.Date;

import help.Customer;

/**
 * @author dbesliu
 * @created 3/28/13
 */
public class Main {

    private static final String DURATION_MESSAGE = "%s sort finished in %d milliseconds";
    private static Customer[] selectionCustomers;
    private static Customer[] insertionCustomers;
    private static Customer[] shellCustomers;

    final private SelectionSort selection;
    final private InsertionSort insertion;
    final private Shell shell;
    final private static Main main = new Main();


    public Main() {
        selection = new SelectionSort();
        insertion = new InsertionSort();
        shell = new Shell();
    }


    public static void main(final String[] args) {
        main.fillCustomers();
        main.processSelectionSort();
        main.processInsertionSort();
        main.processShellSort();
    }


    private void fillCustomers() {
        final Customer alex = createNewCustomer("Alex");
        final Customer denis = createNewCustomer("Denis");
        final Customer alexandr = createNewCustomer("Alexandr");
        final Customer andrei = createNewCustomer("Andrei");
        final Customer stanislav = createNewCustomer("Stanislav");
        final Customer vitalie = createNewCustomer("Vitalie");

        selectionCustomers = new Customer[] { denis, alexandr, stanislav, vitalie, alex, andrei };
        insertionCustomers = new Customer[] { denis, alexandr, stanislav, vitalie, alex, andrei };
        shellCustomers = new Customer[] { denis, alexandr, stanislav, vitalie, alex, andrei };
    }


    private Customer createNewCustomer(final String aName) {
        return new Customer(aName);
    }


    private static void processSelectionSort() {
        final long timeBeforeSort = main.getCurrentTime();
        main.sortWithSelection();
        main.printDuration(timeBeforeSort, "Selection");
        main.printSelectionResult();
    }


    private void processInsertionSort() {
        final long timeBeforeSort = getCurrentTime();
        main.sortWithInsertion();
        main.printDuration(timeBeforeSort, "Insertion");
        main.printInsertionResult();
    }


    private void processShellSort() {
        final long timeBeforeSort = getCurrentTime();
        main.sortWithShell();
        main.printDuration(timeBeforeSort, "Shell");
        main.printShellResult();
    }


    private long getCurrentTime() {
        return new Date().getTime();
    }


    private void printDuration(final long aBeforeSelection, final String aSort) {
        newLine();
        System.out.println(String.format(DURATION_MESSAGE, aSort, getDuration(aBeforeSelection)));
    }


    private long getDuration(final long aBeforeSelection) {
        return getCurrentTime() - aBeforeSelection;
    }


    private void sortWithSelection() {
        selection.sort(selectionCustomers);
    }


    private void printSelectionResult() {
        newLine();
        for (final Customer customer : selectionCustomers) {
            System.out.print(" " + customer);
        }
    }


    private void newLine() {
        System.out.println();
    }


    private void sortWithInsertion() {
        insertion.sort(insertionCustomers);
    }


    private void printInsertionResult() {
        newLine();
        for (final Customer customer : insertionCustomers) {
            System.out.print(" " + customer);
        }
    }


    private void sortWithShell() {
        shell.sort(shellCustomers);
    }


    private void printShellResult() {
        newLine();
        for (final Customer customer : shellCustomers) {
            System.out.print(" " + customer);
        }
    }
}