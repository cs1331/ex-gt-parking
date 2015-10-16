import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class GTParking {

    private List lots;
    private List permits;

    public GTParking() {
        lots = new ArrayList();
        permits = new ArrayList();
    }

    public void addLot(Lot newLot) {
        // TODO
    }

    public void addPermit(Permit newPermit, Lot toLot) {
        // TODO
    }

    public void enterLot(Permit permit, Lot toLot) {
        // TODO
    }

    public void issueCitation(Permit permit) {
        // TODO
    }

    public Permit getPermit(int gtID) throws NoSuchItemException {
        Permit dummy = new Permit("dummy", gtID);
        int index = permits.indexOf(dummy);
        if (-1 == index) {
            throw new NoSuchItemException(String.valueOf(gtID));
        }

        return (Permit) permits.get(index);
    }

    public Lot getLot(int index) throws NoSuchItemException {
        try {
            return (Lot) lots.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchItemException(String.valueOf(index), e);
        }
    }

    public void printLotList() {
        for (int i = 0; i < lots.size(); ++i) {
            System.out.print(i + ". " + lots.get(i));
            if (0 == ((Lot) lots.get(i)).getNumAvailableSpots()) {
                System.out.print(" (FULL)");
            }
            System.out.println();
        }
    }

    // STATIC methods from here down - these handle UI stuff, so the GTParking
    // instance doesn't have to know anything about UI
    //
    public static void main(String[] args) {
        GTParking parking = new GTParking();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        boolean quit = false;

        while (!quit) {
            System.out.println("1. Create a new parking lot");
            System.out.println("2. Create a new permit");
            System.out.println("3. Enter lot (with a permit)");
            System.out.println("4. Issue citation");
            System.out.println("5. Quit");
            System.out.println("Enter a selection:");

            try {
                int menuChoice = Integer.parseInt(in.readLine());

                if (1 == menuChoice) {
                    createNewLot(parking, in);
                } else if (2 == menuChoice) {
                    createNewPermit(parking, in);
                } else if (3 == menuChoice) {
                    enterLot(parking, in);
                } else if (4 == menuChoice) {
                    issueCitation(parking, in);
                } else if (5 == menuChoice) {
                    quit = true;
                } else {
                    System.out.println("Invalid menu selection!");
                }
            } catch (IOException e) {
                System.out.println("I/O Error: try again");
                System.out.println(e);
            }
            System.out.println();
        }
    }

    public static void createNewLot(GTParking parking, BufferedReader in)
        throws IOException {
        System.out.println("Enter new lot name:");
        String lotName = in.readLine().trim();

        int lotCapacity = 0;
        do {
            System.out.println("Enter lot capacity:");
            lotCapacity = Integer.parseUnsignedInt(in.readLine());
            if (lotCapacity < 1) {
                System.out.println("Lot must have capacity > 0");
            }
        } while (lotCapacity < 1);

        Lot parkingLot = null;
        do {
            System.out.println("Is lot gated? (Yes/No)");
            String isLotGated = in.readLine().trim();

            if (isLotGated.equalsIgnoreCase("Yes")
                    || isLotGated.equalsIgnoreCase("Y")) {
                parkingLot = new GatedLot(lotName, lotCapacity);
            } else if (isLotGated.equalsIgnoreCase("No")
                    || isLotGated.equalsIgnoreCase("N")) {
                parkingLot = new Lot(lotName, lotCapacity);
            } else {
                System.out.println("Enter 'Yes' or 'No'.");
            }
        } while (null == parkingLot);

        parking.addLot(parkingLot);
    }

    public static void createNewPermit(GTParking parking, BufferedReader in)
        throws IOException {
        System.out.println("Enter permit holder name:");
        String permitName = in.readLine().trim();

        System.out.println("Enter GTID (90xxxxxxx):");
        int permitGTID = Integer.parseUnsignedInt(in.readLine());

        parking.printLotList();
        System.out.println("Assign permit to which lot?");
        int permitLot = Integer.parseUnsignedInt(in.readLine());

        try {
            parking.addPermit(new Permit(permitName, permitGTID),
                              parking.getLot(permitLot));
        } catch (NoSuchItemException e) {
            System.out.println("Lot not found: " + e.getMessage());
            System.out.println("Permit not added.");
        }
    }

    public static void enterLot(GTParking parking, BufferedReader in)
        throws IOException {
        System.out.println("Enter GTID of the permit entering the lot:");
        int permitGTID = Integer.parseUnsignedInt(in.readLine());

        parking.printLotList();
        System.out.println("Enter which lot?");
        int permitLot = Integer.parseUnsignedInt(in.readLine());

        Permit enteringPermit = null;
        try {
            enteringPermit = parking.getPermit(permitGTID);
        } catch (NoSuchItemException e) {
            System.out.println("Permit not found: " + e.getMessage());
        }

        Lot lotEntered = null;
        try {
            lotEntered = parking.getLot(permitLot);
        } catch (NoSuchItemException e) {
            System.out.println("Lot not found: " + e.getMessage());
        }

        if (null != enteringPermit && null != lotEntered) {
            parking.enterLot(enteringPermit, lotEntered);
        } else {
            System.out.println("Lot not entered.");
        }
    }

    public static void issueCitation(GTParking parking, BufferedReader in)
        throws IOException {
        System.out.println("Enter GTID of the permit to ticket:");
        int permitGTID = Integer.parseUnsignedInt(in.readLine());

        try {
            parking.issueCitation(parking.getPermit(permitGTID));
        } catch (NoSuchItemException e) {
            System.out.println("Permit not found: " + e.getMessage());
            System.out.println("Citation not issued.");
        }
    }
}
