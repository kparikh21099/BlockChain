

/*-------------------------HEADER-------------------------------
1. Name / Date: Kruti Parikh / 1st Nov 2022

2. Version Java
java vresion "18.0.2.1"


3. Command-line compilation steps:
>javac -cp "gson-2.8.2.jar" Blockchain.java
>java -cp ".;gson-2.8.2.jar" Blockchain

4. Instructions to run this program:
In the command Promt
Execution Steps:
> java cp ".:gson-2.8.6.jar" Blockchain 0
> java cp ".:gson-2.8.6.jar" Blockchain 1
> java cp ".:gson-2.8.6.jar" Blockchain 2

5. List of files needed for running the program.
 a. checklist-block.html
 b. Blockchain.java
 c. BlockchainLog.txt
 d. BlockchainLedgerSample.json
 e. BlockInput0.txt, BlockInput1.txt, BlockInput2.txt

6. Notes:
 a) Tried implementing console commands for credit, verification of entoire block and list of records

7. Web Sources Credits - THANKS!
(There are some of the links i reffered to before  starting the assignment and also there are other links from where i found solutions to some of my queries regarding code!!)
Some of these are the websites that some studedents posted on discussion forum. Thanks to them....I found them useful and intresting!

----------------------------------------------------------*/
// Import -- this is a Java library to convert java instances into their JSON representation and also used to convert JSON string to equi JAva instance
import com.google.gson.Gson;
// Import -- this is a Java library to convert java instances into their JSON representation and also used to convert JSON string to equi JAva instance
import com.google.gson.GsonBuilder;
// Import --  java doesn't provide way to represent generic clases, so this class allows the same(TypeToken)
import com.google.gson.reflect.TypeToken;
// Import -- for java input-output libraries
import java.io.*;
// Import -- these includes raw types, parameterized types, array types, type variable and primitive types.
import java.lang.reflect.Type;
// Import -- the network server socket libraries
import java.net.ServerSocket;
// Import -- the java socket libraries
import java.net.Socket;
// Import -- (these are guareented to be availabe on every impl of java platform) the java constant charset libraries for UTF-8
import java.nio.charset.StandardCharsets;
// Import -- java security libraries that provides classes and interfaces for the security framework
import java.security.*;
// Import -- java security library for providing encoding to public key
import java.security.spec.X509EncodedKeySpec;
// Import -- java utility libraries
import java.util.*;
// Import -- java blocking queue libraries
import java.util.concurrent.BlockingQueue;
// Import --  the java priority blocking queue libraries
import java.util.concurrent.PriorityBlockingQueue;

// We declare the blockchain -- the main class
public class Blockchain {
    // main method of the public class
    public static void main(String[] args) {

        // Queue length is defined -- number of incoming request that can be stored in queue
        int Qsize = 8;
        // process Id declared
        int pId;
        if (args.length < 1) {
            pId = 0;    // if there is no argument passed we can take defaultn as 0
        }
        // based on arg in the cmd processes 0,1, 2 will run 
        switch (args[0]) {
            // case check if it is 0, assign 0 to pid for further
            case "0":
                pId = 0;
                break;
             // case check if it is 1, assign 1 to pid for further
            case "1":
                pId = 1;
                break;
             // case check if it is 2, assign 2 to pid for further
            case "2":
                pId = 2;
                break;
            // the default process id it takes as 0 if no argument is passed.
            default:
                pId = 0;
                break;
        }

        BlockchainToDo bcTaskToDo = new BlockchainToDo(pId);
    }
}

// Block record class , we use serializable interface -- it makes it easy to store and send objects
class BlockLedger implements Serializable {
 
    // fields that coressponds to the data items of the text file
    private String bc_Id;
    private String signatureBlock_ID;
    private String currentTime;
    private String blockNum;
    private String fName;
    private String lName;
    private String DOB;
    private String socSecNum;
    private String medicalDiagnosis;
    private String medicalTreatment;
    private String medicalPrescription;
    private String hashMaker;
    private String hashSignatureMaker;
    private String formerHashVal;
    private String victoryHashValue;
    private String victorySignaturedHashValue;
    private String randomSeedValue;
    // Used to store the ID of the process that will validate the block to grant credit
    private String processIdentificationVer;
    private String processCreation;
    private UUID uuid;

    // getter setter for above data fields
    
    // block id variable's getter method
    public String getBc_Id() {return bc_Id;}

    // block id variable's setter method
    public void setBc_Id(String Bc_Id) {
        this.bc_Id = Bc_Id;
    }

    // signed block id's getter method
    public String getSignatureBlock_ID() {return signatureBlock_ID;}

    // signed block id's setter method
    public void setSignatureBlock_ID(String signatureBlock_ID) {
        this.signatureBlock_ID = signatureBlock_ID;
    }

    // currentTime variable's getter method
    public String getCurrentTime() {return currentTime;}

    // currentTime variable's setter method
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    // block number variable's getter method
    public String getBlockNum() {return blockNum;}

    // block number variable's setter method
    public void setBlockNum(String blockNum) {
        this.blockNum = blockNum;
    }

    // first name variable's getter method
    public String getFName() {return fName;}

    // first name variable's setter method
    public void setFName(String fName) {
        this.fName = fName;
    }

    // last name variable's getter method
    public String getLName() {return lName;}

    // last name variable's setter method
    public void setLName(String lName) {
        this.lName = lName;
    }

    // date of birth variable's getter method
    public String getDOB() {return DOB;}

    // date of birth variable's setter method
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    // ssn number variable's getter method
    public String getSocSecNum() {return socSecNum;}

    // ssn number variable's setter method
    public void setSocSecNum(String socSecNum) {
        this.socSecNum = socSecNum;
    }

    // medical diagnosis variable's getter method
    public String getMedicalDiagnosis() {return medicalDiagnosis;}

    // medical diagnosis variable's setter method
    public void setMedicalDiagnosis(String medicalDiagnosis) {
        this.medicalDiagnosis = medicalDiagnosis;
    }

    // treatment variable's getter method
    public String getMedicalTreatment() {return medicalTreatment;}

    // treatment variable's setter method
    public void setMedicalTreatment(String medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }

    // medical prescription variable's getter method
    public String getMedicalPrescription() {return medicalPrescription;}

    //  medical prescription variable's setter method
    public void setMedicalPrescription(String medicalPrescription) {
        this.medicalPrescription = medicalPrescription;
    }

    // hash maker variable's getter method
    public String getHashMaker() {return hashMaker;}

    // hash maker variable's setter method
    public void setHashMaker(String hashMaker) {
        this.hashMaker = hashMaker;
    }

    //  hash signed maker variable's getter method
    public String getHashSignatureMaker() {return hashSignatureMaker;}

    // setter method for hash signed maker variable
    public void sethashSignatureMaker(String hashSignatureMaker) {
        this.hashSignatureMaker = hashSignatureMaker;
    }

    // getter method for previous hash value variable
    public String getFormerHashVal() {return formerHashVal;}

    // setter method for previous hash value variable
    public void setFormerHashVal(String formerHashVal) {
        this.formerHashVal = formerHashVal;
    }

    // getter method for winning hash value variable
    public String getVictoryHashValue() {return victoryHashValue;}

    // setter method for winning hash value variable
    public void setVictoryHashValue(String victoryHashValue) {
        this.victoryHashValue = victoryHashValue;
    }

    // getter method for winning signed hash value variable
    public String getVictorySignaturedHashValue() {return victorySignaturedHashValue;}

    // setter method for winning signed hash value variable
    public void setVictorySignaturedHashValue(String victorySignaturedHashValue) {
        this.victorySignaturedHashValue = victorySignaturedHashValue;
    }

    // getter method for random seed value variable
    public String getRandomSeedValue() { return randomSeedValue; }

    // setter method for random seed value variable
    public void setRandomSeedValue(String randomSeedValue) {
        this.randomSeedValue = randomSeedValue;
    }

    // getter method for process id verification variable
    public String getProcessIdentificationVer() {return processIdentificationVer;}

    // setter method for process id verification variable
    public void setProcessIdentificationVer(String processIdentificationVer) {
        this.processIdentificationVer = processIdentificationVer;
    }

    // getter method to create a process variable. We are required to know this to verify the signature
    public String getProcessCreation() {
        return processCreation;
    }

    // setter method for process creation variable
    public void setProcessCreation(String processCreation) {
        this.processCreation = processCreation;
    }

    // getter method for UUID variable
    public UUID getUuid() { return uuid; }

    // setter method for UUID variable
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "BlockLedger{" +
                "Bc_Id='" + bc_Id + '\'' +
                ", signatureBlock_ID='" + signatureBlock_ID + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", blockNum='" + blockNum + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", socSecNum='" + socSecNum + '\'' +
                ", medicalDiagnosis='" + medicalDiagnosis + '\'' +
                ", medicalTreatment='" + medicalTreatment + '\'' +
                ", medicalPrescription='" + medicalPrescription + '\'' +
                ", hashMaker='" + hashMaker + '\'' +
                ", hashSignatureMaker='" + hashSignatureMaker + '\'' +
                ", formerHashVal='" + formerHashVal + '\'' +
                ", victoryHashValue='" + victoryHashValue + '\'' +
                ", victorySignaturedHashValue='" + victorySignaturedHashValue + '\'' +
                ", randomSeedValue='" + randomSeedValue + '\'' +
                ", processIdentificationVer='" + processIdentificationVer + '\'' +
                ", processCreation='" + processCreation + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}

// ckass BlockchainToDo here
class BlockchainToDo {
    // a locally declared process ID variable..... it stores the current process ID that will obtained from the Blockchain class
    
    public static int pId;
    // the total number of processes that can be served. This number can be updated to handle any number of peers...
    public static int allProcess = 3;
    // declaring the server name variable
    public static String serverName = "localhost";
    // a boolean variable that indicates when all processes can begin execution
    // here the initial value is set to false. It will be upated to 'true' one the process starts....
    public static boolean startProcessBoolean = false;
    // declaring a primary key boolean and it will be changed to true when we receive all processes (i.e; 3). 
    // Initially it is set to false
    public static boolean priKeyBoolean = false;
    // stating primary key variable, it will keep the count of number of processes started to change primaryKey boolean
    public static int priKeyCheck = 0;
    // Public key and private key pair stated here 
    public static KeyPair pubPriKeyPair;
    // in the array public Key List public key of each process is added to our
    public static PublicKey[] pubKeyList = new PublicKey[allProcess];
    // asserting a concern blocking off queue for storing unverified blocks (unmarshalled block into java object) from here, every manner will select out the block for fixing puzzle
    public static final PriorityBlockingQueue<BlockLedger> blockChainQ = new PriorityBlockingQueue<>(50, new PriQueueIdentifier());
    // this list is used to store our verified blocks
    public static LinkedList<BlockLedger> verBlockList = new LinkedList<>();
    // this list is used to store our unverified blocks
    public static LinkedList<BlockLedger> unVerBlockList = new LinkedList<>();

    // Setting token indexes for file input data //
    // index 0 for first name
    private static final int indexOfFName = 0;
    // index 1 for last name
    private static final int indexOfLName = 1;
    // index 2 for Date of Birth
    private static final int indexOfDOB = 2;
    // index 3 for Social Security Number
    private static final int indexOfSoshSecNumber = 3;
    // index 4 for Medical diagnosis
    private static final int indexOfMedDiagnosis = 4;
    // index 5 for medical treatment
    private static final int indexOfMedTreatment = 5;
    // index 6 for medical Prescription
    private static final int indexOfMedPrescription = 6;

    // constructor class with single arument(process id in it) that is assigned to local variable
    // it starts and places all ports for specific process and executes the run method
    public BlockchainToDo(int pId) {
        BlockchainToDo.pId = pId;
        //provoking ports class and simultaneously assigning pports to each process Id
        new Ports().setPorts(pId);
        // run method declaration 
        run();
    }

    // run() method implementation
    public void run() {

        // Present text to display on the screen when Blockchain is in execution
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Kruti Parikh's blockchain program has started..");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("You will encounter a pause of 15secs.");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Employing the inputt file here : " + String.format("BlockInput%d.txt\n", pId));
        // creating new thread to start a new server 
        new Thread(new StartMainServer()).start();
        // creating this thread to get public keys for every process alonf with the current one
        new Thread(new pubKeysServer()).start();
        // creating new thread to recieve blocks that are unverified in the priority queue for every process here
        new Thread(new UVBlockServer(blockChainQ)).start();
        // creating new thread to recive new genereted or updatedd blockds
        new Thread(new UBlockchainServer()).start();
        // pausing for 1 second waiting for all processes to be active before
        try {
            Thread.sleep(1000);
        } 
        // handling exceptions
        catch (Exception ec) {
            ec.printStackTrace();
        }

       
        // if condition is true i.e; pid is 2 ....it will start excecutingntj the process
        if (pId == 2) {
            // invokes startAllProcesses() method
            startAllProcesses();
        }

        // generating private public key pair and passing in random vaalue 
        try {
            pubPriKeyPair = createPubPriKeyPair(999);
        } 
        // handling exceptions
        catch (Exception ec) {
            ec.printStackTrace();
        }

        // if start process boolean is false, and waiting for all process to start
        while (!startProcessBoolean) {
            exePause();
        }
        System.out.println("Launching...");
       
        // starting the multicast method where each process share their own public keys
        multiPubKey();
        while (!priKeyBoolean) {
            exePause();
        }
        //invoke dummy block if pId is 0 currently
        if (pId == 0) {
            formingBlockGenesis();
        }
        // starting scanInFile() it helps invoke its own scanInput method
        scanInFile();
        multiCastUVBlockProcesses();
        try {
            Thread.sleep(1000);
        }
        // handling exceptionn
        catch (InterruptedException iec) {
            iec.printStackTrace();
        }

        // creating new thread for process0 process1 process2 to solve the hash puzzle 
        new Thread( new HashPuzzle(blockChainQ)).start();
        // Invoking sleep statement
        try {
            Thread.sleep(15000);
        } catch (Exception exception) {
            //exception handling
            exception.printStackTrace();
        }

        System.out.println("--------------------------------------------");
        System.out.println("Successfully created blockChain Ledger in JSON format.....");
        System.out.println(" For proceeding further please enter commands as mentioned below : ");
        System.out.println("i)      Press 1 for getting the Credits");
        System.out.println("ii)     Press 2 for getting entire blockchain verified");
        System.out.println("iii)    Press 3 for getting List of records");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("\n");
            System.out.println("Please enter command for the process you would further like to proceed :");
            Scanner conCommand = new Scanner(System.in);
            String command = conCommand.nextLine();
            switch (command) {
                case "1":
                    getCredits();
                    break;
                case "2":
                    blockChainVerified();
                    break;
                case "3":
                    blockLedgerList();
                    break;
                // if user doesn't enter a proper command .....this message will be displayed ...to enter a valid command!
                default : 
                    System.out.println("Please enter a valid command...");
                    System.out.println("Try Again....Enter 1, 2 or 3 according to above instructions");
                    break;
            }
        }
    }

    // This method is called when user enters 3 -- command for getting List of records 
    private void blockLedgerList() {
        Gson gson = new Gson();
        LinkedList<BlockLedger> listOfRecords;
        try {
            // Here scaning the file blockChain Ledgerr
            Reader   fileIn = new FileReader("BlockchainLedger.json");
            Type formatType = new TypeToken<LinkedList<BlockLedger>>() {}.getType();
            // cmaking set of records
            listOfRecords = gson.fromJson(fileIn, formatType);
            System.out.println("Below verified records are present in our BlockChain Ledger (latest first):");
            System.out.println("Below are the verified records of Blockchain Ledger (it will display the latest record first) :");
            // creating the reitration of the set of records
            Iterator<BlockLedger> itrateBlockLedger = listOfRecords.iterator();
            int recordCount = listOfRecords.size();
            // Itrating the list and printing it in console
            while (itrateBlockLedger.hasNext()) {
                BlockLedger recordIterator = itrateBlockLedger.next();
                System.out.printf("%d. " + "%s " + "%s " + "%s " + "%s " + "%s " + "%s " + "%s " +  "%s \n", recordCount,
                        recordIterator.getCurrentTime(), recordIterator.getFName(), recordIterator.getLName(),
                        recordIterator.getDOB(), recordIterator.getSocSecNum(), recordIterator.getMedicalDiagnosis(),
                        recordIterator.getMedicalTreatment(), recordIterator.getMedicalPrescription());
                        recordCount--;
            }
        } 
        // handling exceptions
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    // This method verifies the full blockchain block fields 
    // This method will be called when user enters 2 in the command
    private void blockChainVerified() {
        boolean flag = false;
        Gson gson = new Gson();
        LinkedList<BlockLedger> listOfRecords;
        try {
            Reader fileIn = new FileReader("BlockchainLedger.json");
            Type formatType = new TypeToken<LinkedList<BlockLedger>>() {}.getType();
            // Storing the records in the ledger in the temporary LinkedList
            listOfRecords = gson.fromJson(fileIn, formatType);

            // running through the listofRecord's loop
            for (BlockLedger records : listOfRecords) {
                String numOfRecords = records.getBlockNum();
                // This condition is so that the records don't get repeted -- its skips the repeted records
                if(!numOfRecords.equals("1"))
                {
                    //System.out.println("Block Number: " + numOfRecords);
                    String dataString;
                    // Taking the block record in the string format
                    String BlockLedger = records.getBc_Id() +
                    records.getFName() + records.getLName() + records.getSocSecNum() +
                    records.getDOB() + records.getMedicalDiagnosis() +
                    records.getMedicalTreatment() + records.getMedicalPrescription() + records.getProcessCreation();
                    try {
                        // here we will add blockLedger, previous hash value and random value 
                        String finalBlock = BlockLedger;
                        finalBlock = finalBlock + records.getFormerHashVal();
                        String concatedBlockInfo = finalBlock + records.getRandomSeedValue();

                        // dreiving new hash of new generated block
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        byte[] hashValueBytes = md.digest(concatedBlockInfo.getBytes(StandardCharsets.UTF_8));
                        dataString = HashPuzzle.byteArray2Str(hashValueBytes);

                        //this condition will check if current hash value and new hash value are same
                        if (!dataString.equals(records.getVictoryHashValue())) {
                            System.out.println("Verification for hash (SHA-256) failed.\n");
                            flag = true;
                        }

                        // to get numerical value of first 16 bits
                        int thresholdNumber = Integer.parseInt(dataString.substring(0, 4), 16);

                        // giving a check if threshholdNumber is not exceeding its limit 
                        // checking if the puzzel is solved by the newly created hash
                        if (!(thresholdNumber < 25000)) {
                            System.out.println("Opps...not able to solve hash puzzel");
                            flag = true;
                        }

                        try {
                            boolean isHashAuthenticated = verifySignature(records.getVictoryHashValue().getBytes(),
                                    pubKeyList[Integer.parseInt(records.getProcessIdentificationVer())],
                                    Base64.getDecoder().decode(records.getVictorySignaturedHashValue()));
                            // Based on hash verification, display message accordingly
                            if(isHashAuthenticated) {
                                System.out.println("The BLOCK ID : " + numOfRecords + " Hash (SHA-256) signature verified successfully");
                            }
                            else {
                                System.out.println("The BLOCK ID : " + numOfRecords + " Hash (SHA-256) signature verification failed!");
                                flag = true;
                            }

                            boolean isBlockIdAuthenticated = verifySignature(records.getBc_Id().getBytes(),
                                    pubKeyList[Integer.parseInt(records.getProcessCreation())],
                                    Base64.getDecoder().decode(records.getSignatureBlock_ID()));

                            // message is been displayed according to the  BlockId signature varification
                            if(isBlockIdAuthenticated) {
                                System.out.println("The BLOCK ID : " + numOfRecords + " Yeahh...Successfully verified the signaturee..");
                            }
                            else {
                                System.out.println("The BLOCK ID : " + numOfRecords + "Whoopss... Failed to verify signature..");
                                flag = true;
                            }
                        } 
                        // handling exception
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } 
                    // handling the exception
                    catch (NoSuchAlgorithmException nsae) {
                        nsae.printStackTrace();
                    }
                }
            }
        }
        // handling exception
        catch (Exception e) {
            e.printStackTrace();
        }

        // this message will be displayed to help user know that entire blockchain has been verified
        String finalDisplay = (!flag) ? "Successfuly completed the verification of the whole BlockChain!" : "Errors in the" +
                "ledger exists!";
        System.out.println(finalDisplay);
    }

    //A method that goes through the blockchain ledger, records which process verified which block, and displays the final tally on the console.
    // This method is invoked when user enters 1 for the process further 
    private void getCredits() {
        Gson gson = new Gson();
        LinkedList<BlockLedger> listOfRecords;
        // declaring array variable for store the credit score for each process
        int[] creditTotal = new int[allProcess];
        try {
            Reader fileIn = new FileReader("BlockchainLedger.json");
            Type formatType = new TypeToken<LinkedList<BlockLedger>>() {}.getType();
            // writing the blocks into a temporary linked list - listOfRecords
            listOfRecords = gson.fromJson(fileIn, formatType);
            // looping through the list to count
            for (BlockLedger rec : listOfRecords) {
                if (rec.getProcessIdentificationVer() != null) {
                    // checking the process number that verified the block and incrementing the counter
                    // accordingly
                    int processNumber = Integer.parseInt(rec.getProcessIdentificationVer());
                    creditTotal[processNumber]++;
                }
            }
            // Displaying the final credit count on the console
            System.out.println("Verification Credit received by Processes:");
            System.out.printf("Credit Score for process0 : %d" + "\n" +
                            "Credit  score for process1 : %d" + "\n" +
                            "Credit score for process2: %d", creditTotal[0],
                    creditTotal[1], creditTotal[2]);
        } 
        // handling input output exception
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    // multiCastUVBlockProcesses method so that unverified blocks get multicast
    public void multiCastUVBlockProcesses() {
        Socket multiCastSocket;
        PrintStream recByServer;
        BlockLedger shortTermBlockRecord;
        // this loop is through all the unverified blockson the records
        Iterator<BlockLedger> iteratorVar = unVerBlockList.iterator();
        try {
            while (iteratorVar.hasNext()) {
                shortTermBlockRecord = iteratorVar.next();
                // json builder is used to create json format
                String blockLed = jsonBuilder(shortTermBlockRecord);
                for (int i = 0; i < allProcess; i++) {
                    multiCastSocket = new Socket(serverName, Ports.portForUVBServ + i); //begin connection
                    recByServer = new PrintStream(multiCastSocket.getOutputStream());
                    recByServer.println(blockLed);   //block rec to every process
                    recByServer.flush();            // clears cachee
                    multiCastSocket.close(); // sock connection closed
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to make the thread sleep for sometime till all processes are on same page for execution
    public static void exePause() {
        try {
            Thread.sleep(1000);
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    // If matching block exists it compares the records with blockchain ledger 
    public static boolean isMatching(BlockLedger BlockLedgerIn) {
        // creating a local variable to bind the block record passed as an argument to the function call
        BlockLedger checkRec = BlockLedgerIn;
        // Loop through the blockchain ledger
        for (BlockLedger BlockLedger : verBlockList) {
            // compares if the passed blockLed with every record already exsisting in ledger using block id fied
            if (checkRec.getBc_Id().equals(BlockLedger.getBc_Id()))
                return true;
        }
        return false;
    }

    // to create a public and private key pair
    public static KeyPair createPubPriKeyPair(long randomSeed) throws Exception {
        // key pair generators are generated using getInstance method, here it is using RSA(Rivest-Shamir-Aldeman) encryption algorithm
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        // Creating a SecureRandom object and re-seeding it by setting random seed value
        SecureRandom secRandObject = SecureRandom.getInstance("SHA1PRNG", "SUN");
        secRandObject.setSeed(randomSeed);
        // Initializing the key size for our keyGenerator instance
        keyGenerator.initialize(1024, secRandObject);
        // returns createPubPriKeyPair() method which is used to generate private public key pair
        return (keyGenerator.generateKeyPair());
    }

    // Public key multicast method - receives the public key and broadcasts the key to other processes.
    // It also has a process ID attached to it to help us decide which process public key was used when verifying the block
    public void multiPubKey() {
        Socket multiCastSocket; // socket variable
        PrintStream recByServer; // provides method to write data to another streams
        byte[] pubKey = pubPriKeyPair.getPublic().getEncoded(); // fetching bytes
        String strpubKey = Base64.getEncoder().encodeToString(pubKey); // recasting byte digital sign to string form
        System.out.println("Public key has been generated now for multicasting " + strpubKey); // print statement to display
        try {
            // for looping through all processes
            for (int k = 0; k < allProcess; k++) {
                // creating socket object 'multiCastSocket' and passing in server name and public key server port number
                multiCastSocket = new Socket(serverName, Ports.portKeyServ + k);
                // creating printStream object and assigning output stream on the above socket for multicasting the keys
                recByServer = new PrintStream(multiCastSocket.getOutputStream());
                // concatenating process ID and public key str
                String processIDpubKey = pId + " " + strpubKey;
                // sending the public key to each servers/processes
                recByServer.println(processIDpubKey);
                // flushing the printStream
                recByServer.flush();
                // closing the socket
                multiCastSocket.close();
            }
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    // this boolean method startAllProcesses to send a start signal to the processes, at the end as a return we switch the value of boolean
    public boolean startAllProcesses() {
        Socket onSetSock; 
        PrintStream recByServer;
        try {
            // for looping through number of processes
            for (int k = 0; k < allProcess; k++) {
                // initializing onSetSock with localhost and startServer port base + process number
                onSetSock = new Socket(serverName, Ports.portStartServ + k);
                recByServer = new PrintStream(onSetSock.getOutputStream());
                recByServer.println("Startt...");
                // Printing out display message on Process 2
                System.out.println("Sending start signn...");
                recByServer.flush();    // clears cache
                onSetSock.close();      // socket closed
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    // A method to create a dummy block that will be the basis (starting block - starting point) of all processes. 
    // By setting initial values, we ensure that each block gets common data
    public static void formingBlockGenesis() {

        String SHA256Details;
        BlockLedger blockLed = new BlockLedger();
        Date dateTime = new Date(); // sys formated date and time
        long timeDetail = dateTime.getTime();

        String strtimeDetail = String.valueOf(timeDetail); // Converting time to string form
        // getting the currentTime value out of time by concatenating the process ID along with it. 
        // this will help solve the problem of the same current times of the blockBc_Id records
        String timeMarked = strtimeDetail + "." + pId;
        String setUUID = UUID.randomUUID().toString(); // setting random uuid

        // The default values marking
        blockLed.setBc_Id(setUUID);
        blockLed.setCurrentTime(timeMarked);
        blockLed.setFName("Paxton");
        blockLed.setLName("Friedrich");
        blockLed.setSocSecNum("911-00-911");
        blockLed.setDOB("1999.02.10");
        blockLed.setMedicalDiagnosis("Tumor");
        blockLed.setMedicalTreatment("Treatment");
        blockLed.setMedicalPrescription("Priscribed");
        blockLed.setFormerHashVal("222222222");
        blockLed.setBlockNum("2");

        // String format of block ledger values is being prepared
        String blockLedger = blockLed.getBc_Id() +
                blockLed.getFName() +
                blockLed.getLName() +
                blockLed.getSocSecNum() +
                blockLed.getDOB() +
                blockLed.getMedicalDiagnosis() +
                blockLed.getMedicalTreatment() +
                blockLed.getMedicalPrescription();

        // Calling the Message Digest function on our string block record by calling the MD2StringBuilder method
        SHA256Details = MD2StringBuilder(blockLedger);

        // for the clone data situating newly created hash value of block as winning hash value
        blockLed.setVictoryHashValue(SHA256Details);

        // attaching 1st clone data to ledger at zeroth indexx
        verBlockList.add(0, blockLed);
        // showing message on command promt asserting the present size of ledger
        System.out.println("Here is the size of Ledger...: " + verBlockList.size());

        // granting permission for clone block data to be written on the ledger
        if (pId == 0) {
            // displaying message on the terminal on the action being performed
            System.out.println("Creating first block to Ledger - Clone Entry...");
            transferBlockToLedger(blockLed, "verBlockListUpdate");
            converttTOJSON();     // converting to Json on te drive
        }
    }

    public static void transferBlockToLedger(BlockLedger blockLed, String functionality) {
        Socket socSocket;
        PrintStream recByServer;
        // switch case
        switch (functionality) {
            case "verBlockListUpdate":
                try {
                    // for looping by every process
                    for (int k = 0; k < allProcess; k++) {
                        // setting updated blockchain server port socket to send verified block                        
                        socSocket = new Socket(serverName, Ports.portForUpdatedBC + k);
                        recByServer = new PrintStream(socSocket.getOutputStream());
                        // assembling data as JSON object and sending it to processes port
                        recByServer.println(jsonBuilder(blockLed));
                        // Showing message on cmd console
                        System.out.println("Broadcasting of the verified blocks : " + blockLed.getBc_Id());
                        recByServer.flush(); // clears cache
                        socSocket.close(); // close socket connections
                    }
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
                break;
            case "reVerBlockList":
                try {
                    // for looping by total processs
                    for (int v = 0; v < allProcess; v++) {
                        // setting an unauthenticated block server port socket to send blocks that need to be reauthenticated
                        socSocket = new Socket(serverName, Ports.portForUVBServ + v);
                        recByServer = new PrintStream(socSocket.getOutputStream());
                        // assembling data as JSON object and sending it to processes port
                        recByServer.println(jsonBuilder(blockLed));
                        // Showing message on cmd console
                        System.out.println("Block is being broadcast: " + blockLed.getBc_Id());
                        recByServer.flush();  // cache clear
                        socSocket.close();
                    }
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
                break;
        }
    }

    // Method used to marshall java object to JSON using gson. Input to this method is the blockLed and is passed
    // toJson for conversion; return the json format output
    public static String jsonBuilder(BlockLedger blockLed) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String toJSON = gson.toJson(blockLed);
        return toJSON;
    }

    //A method that loads 3 input files - BlockInput0.txt, BlockInput1.txt, BlockInput2.txt. 
    // It then creates a token for each data value and uses it to create an unverified block. 
    // This block will have a hash value of the SHA256 string which will later help in creating a digital signature for auditing
    public static void scanInFile() {
        // restructuring of the input file based on the gibven pid accordingly
                String fileIn = String.format("BlockInput%d.txt", pId);
        try {
            BufferedReader dataIn = new BufferedReader(new FileReader(fileIn)); // read input file data
            // creating tokens to place the input data as per the defined java variables for it
            String[] dataTokes;
            String inputStrData;
            String bcUUID;
            try {
                // if the dataIn is not null
                while ((inputStrData = dataIn.readLine()) != null) {
                    Date dateTime = new Date();
                    BlockLedger blockLed = new BlockLedger();
                    // declaring and initializing timeDetail variable - fetches current time
                    long timeDetail = dateTime.getTime();
                    // converting time value into string format
                    String currentTime = String.valueOf(timeDetail);
                    // creating the currentTime out of time value by appending the process ID along with it.
                    String timeStampprocessID = currentTime + "." + pId;
                    bcUUID = UUID.randomUUID().toString();
                    // Splitting input data into tokens and storing them in String[] format
                    dataTokes = inputStrData.split(" +");
                    // declaring the signatureBlock variable
                    // 
                    String signatureBlock = "";
                    try {
                        // Calling the signData() method to apply a digital signature to our block
                        byte[] digitalSignature = signData(bcUUID.getBytes(), pubPriKeyPair.getPrivate());
                        // The digitalSign is then Base64 encoded and assigned to the signatureBlock variable
                        signatureBlock = Base64.getEncoder().encodeToString(digitalSignature);

                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }

                    // Set values ​​of data fetched and updated with UUID, signatureBlockID, creator process ID, current timestamp
                    blockLed.setBc_Id(bcUUID);
                    blockLed.setCurrentTime(timeStampprocessID);
                    blockLed.setSignatureBlock_ID(signatureBlock);
                    blockLed.setProcessCreation(String.valueOf(pId));
                    blockLed.setFName(dataTokes[indexOfFName]);
                    blockLed.setLName(dataTokes[indexOfLName]);
                    blockLed.setSocSecNum(dataTokes[indexOfSoshSecNumber]);
                    blockLed.setDOB(dataTokes[indexOfDOB]);
                    blockLed.setMedicalDiagnosis(dataTokes[indexOfMedDiagnosis]);
                    blockLed.setMedicalTreatment(dataTokes[indexOfMedTreatment]);
                    blockLed.setMedicalPrescription(dataTokes[indexOfMedPrescription]);

                    // The block is added to the unverified block list
                    unVerBlockList.add(blockLed);

                    // Creating a string format of our unauthenticated block record values ​​that will later help in creating a SHA256 hash value
                    String blockLedStr = blockLed.getBc_Id() + blockLed.getFName() + blockLed.getLName() +
                            blockLed.getSocSecNum() + blockLed.getDOB() + blockLed.getMedicalDiagnosis() +
                            blockLed.getMedicalTreatment() + blockLed.getMedicalPrescription() + blockLed.getProcessCreation();

                    // creating a block data hash function by calling the MD2StringBuilder method
                    String hashDigestString = MD2StringBuilder(blockLedStr);

                    // declaring signed hash variable
                    String hashSignature = "";

                    // Signing the final unverified block using hashDigestString and private key
                    try {
                        byte[] digitalSign2 = signData(hashDigestString.getBytes(), pubPriKeyPair.getPrivate());
                        hashSignature = Base64.getEncoder().encodeToString(digitalSign2);
                    } catch (Exception excpt) {
                        // exception handling
                        excpt.printStackTrace();
                    }
                    // setting the hash field of the creator using hashDigestString
                    blockLed.setHashMaker(hashDigestString);
                    //setting the hash signed by the creator using hashSignature
                    blockLed.sethashSignatureMaker(hashSignature);
                    exePause();  // invoking sleep method
                }
            } catch (IOException ioexp) {
                ioexp.printStackTrace();
            }

        } 
        // file not found exception handled
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // A method of performing a SHA256 hash on a block chain; which is then converted to a hexadecimal string and passed back to the calling function
    private static String MD2StringBuilder(String blockLedStr) {
        StringBuffer hexaDecimalString;
        String hashDigestString = "";
        try {
            // creating a messageDigest object using the SHA256 algorithm for hashing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // passing the blockLedStr data to the md object created above
            // The update() method is called to modify the digest using the specified number of bytes
            md.update(blockLedStr.getBytes());
            // hash calculation on the updated md object (output will be in byte[] format)            
            byte[] bytevalues = md.digest();
            // Convert byte data to hex format
            hexaDecimalString = new StringBuffer();
            for (byte bdValue : bytevalues) {
                hexaDecimalString.append(Integer.toString((bdValue & 0xff) + 0x100, 16).substring(1));
            }
            hashDigestString = hexaDecimalString.toString();
        } catch (NoSuchAlgorithmException nsaex) {
            nsaex.printStackTrace();
        }
        return hashDigestString;
    }

    // This method is used to sign data using private key
    public static byte[] signData(byte[] byteRecords, PrivateKey priKeyValue)
            throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature signatory = Signature.getInstance("SHA1withRSA");
        signatory.initSign(priKeyValue);
        signatory.update(byteRecords);
        return (signatory.sign());
    }

    //A method that allows processes to verify that data has been signed with a public key or not
    public static boolean verifySignature(byte[] byteRecords, PublicKey pubKey, byte[] decode)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signatory = Signature.getInstance("SHA1withRSA");
        signatory.initVerify(pubKey);
        signatory.update(byteRecords);
        return (signatory.verify(decode));
    }

    // verified BlockList is our LinkedList that contains verified blocks to be added to the ledger
    public static void converttTOJSON() {
        // converting java object to JSON and writing it on disk
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(BlockchainToDo.verBlockList);
        // Creating output file name - BlockchainLedger.json
        // making file named BlockchainLedger.json
        try (FileWriter writeData = new FileWriter("BlockchainLedger.json")) {
            gson.toJson(BlockchainToDo.verBlockList, writeData);
        } catch (IOException ioException) {
            // exception handling
            ioException.printStackTrace();
        }
    }
}

// this class Ports is for setting the values of all the processes
class Ports {
    public static int portStartServ = 4600;
    public static int portKeyServ = 4710;
    public static int portForUVBServ = 4820;
    public static int portForUpdatedBC = 4930;

    public static int portStartServer;
    public static int portKeyServer;
    public static int portUBServer;
    public static int blockChainPortServ;

    // setPorts() method that accepts a process ID and sets all ports accordingly. Subsequent format: portBase + process ID
    public void setPorts(int pId) {
        portStartServer = portStartServ + pId;
        portKeyServer = portKeyServ + pId;
        portUBServer = portForUVBServ + pId;
        blockChainPortServ = portForUpdatedBC + pId;

    }
}

// Comparator class that will have our priority queue to arrange the blocks based on the current time
class PriQueueIdentifier implements Comparator<BlockLedger> {
    @Override
    public int compare(BlockLedger blockLedger1, BlockLedger blockLedger2) {
        String dateI = blockLedger1.getCurrentTime();
        String dateII = blockLedger2.getCurrentTime();
        if (dateI.equals(dateII)) {
            return 0;
        }
        if (dateI == null) {return -1;}
        if (dateII == null) {
            return 1;
        }
        return dateI.compareTo(dateII);
    }
}

// Starts the master class which invokes the worker class. This server will help start all processes - 
//by changing the value for startProcessBoolean in its worker class. It will accept the command - 
//start from P2 and switch the boolean to True - allowing other processes to continue to function
class StartMainServer implements Runnable {
    public void run() {
        // defined queue length
        int Qsize = 8;
        // declaring socket variable
        Socket socket;
        // displaying message on the terminal stating start of main server
        System.out.println("Main server started at: " + Ports.portStartServer);
        try {
            // assigning serverSocket object - its port number and queue length
            ServerSocket serverSocket = new ServerSocket(Ports.portStartServer, Qsize);
            while (true) {
                // accepting request
                socket = serverSocket.accept();
                // spawning a new worker thread and invokes worker class run()
                new SMSWorker(socket).start();
            }
        } catch (IOException ioException) {
            // exception handling
            ioException.printStackTrace();
        }
    }
}

// Worker class for StartMainServer class
class SMSWorker extends Thread {
    // declaring socket variable
    Socket socket;

    // constructor declaration - assigning socket to locally defined socket variable
    public SMSWorker(Socket socket) {
        this.socket = socket;
    }

    // run() method
    public void run() {
        try {
            //System.out.println("Inside start main server worker class!!!");
            // reading in input data from the socket
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Once input data is received, changes the startProcessBoolean boolean to true. Assuming that no other
            // commands will be sent over to this specific server.
            String dataRead = dataIn.readLine();
            BlockchainToDo.startProcessBoolean = true;
            // closes the socket connection
            socket.close();
        } catch (IOException ioException) {
            // exception handling
            ioException.printStackTrace();
        }
    }
}

class pubKeysServer implements Runnable {
    public void run() {
        // queue length is defined
        int Qsize = 8;
        Socket socket;
        //display a message on the terminal announcing the launch of the public key server port. 
        //It means the server is up and will now accept multi-way public keys from individual processes
        System.out.println("Launching Public Keys Server at port: " + Ports.portKeyServer);
        try {
            ServerSocket serverSocket = new ServerSocket(Ports.portKeyServer, Qsize);
            while (true) {
                // accepting request
                socket = serverSocket.accept();
                new PubKeyServWorker(socket).start();
            }
        } catch (IOException iecx) {
            iecx.printStackTrace();
        }
    }
}

// Worker class for Public Key Server class
class PubKeyServWorker extends Thread {
    // declaring key socket variable
    Socket pubKeySock;

    // constructor declaration - assigning socket to locally defined socket variable
    public PubKeyServWorker(Socket socket) {
        this.pubKeySock = socket;
    }

    public void run() {
        try {
            // reading data from the input stream
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(pubKeySock.getInputStream()));
            // Splitting the dataIn recieved in String array
            String[] dataRead = dataIn.readLine().split(" ");
            // Since the process ID is an int - we convert the string form to int form for future indexing purposes
            int pId = Integer.parseInt(dataRead[0]);

            //Decoding the public key store at index position 1 in byte form
            // Convert from string to byte[] format
            byte[] pubKeyB = Base64.getDecoder().decode(dataRead[1]);
            X509EncodedKeySpec publicSpectator = new X509EncodedKeySpec(pubKeyB);
            KeyFactory publicKeyFActory = KeyFactory.getInstance("RSA");
            PublicKey replacedKey = publicKeyFActory.generatePublic(publicSpectator);

            // Then the public key of each process is added to our pubKeyList
            BlockchainToDo.pubKeyList[pId] = replacedKey;
            // incrementing counter with one accordingly
            BlockchainToDo.priKeyCheck++;
            // if we get all the 3 processes, then changing the boolean to true
            if (BlockchainToDo.priKeyCheck == 3) {
                BlockchainToDo.priKeyBoolean = true;
            }
            // Displaying message on terminal confirming public key has been received for all 3 processes
            System.out.println("Public key is recieved for Process Id : " + pId);
            pubKeySock.close(); // close coonnectionn
        } 
        // handling
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//An unauthenticated Block Server class that invokes the appropriate worker class
//Accepts blocks that are read by each process via an input file or those blocks need to be revalidated due to a change in the blockchain ledger
class UVBlockServer implements Runnable {

    // declaring local queue variable for blockchain
    BlockingQueue<BlockLedger> blockChainQ;

    // constructor declaration that assigns the queue to our locally defined block queue variable
    public UVBlockServer(BlockingQueue<BlockLedger> blockChainQ) {
        this.blockChainQ = blockChainQ;
    }

    @Override
    public void run() {
        // defined queue length - number of incoming request that can be stored in queue
        int Qsize = 8;
        // declaring UVBSocket socket variable
        Socket UVBSocket;

        // Display message stating the launch of unverified block server along with its port number
        System.out.println("Launching the Unverified Block Server input thread using " + Ports.portUBServer);
        try {
            // creating server socket object UVBlockServer
            ServerSocket UVBlockServer = new ServerSocket(Ports.portUBServer, Qsize);
            while (true) {
                // accepting request and receiving a new unverified block to store in the queue
                UVBSocket = UVBlockServer.accept();
                // spawning worker thread to process the incoming request
                new UVBlockServerWorker(UVBSocket).start();
            }
        } catch (IOException ioException) {
            // exception handling
            ioException.printStackTrace();
        }
    }
}

// Worker class for Un-Verified Block Server class
// Receives UVB and places into priority queue
class UVBlockServerWorker extends Thread {
    // declaring uvbSocket socket
    Socket uvbSocket;

    // constructor declaration assigning uvbSocket to locally defined socket variable
    public UVBlockServerWorker(Socket uvbSocket) {
        this.uvbSocket = uvbSocket;
    }

    @Override
    public void run() {
        try {
            // Reading in input data from uvbSocket
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(uvbSocket.getInputStream()));
            // declaring input string variable
            String inputString;
            // creating gson object
            Gson gson = new Gson();
            // creating string buffer object
            StringBuffer strBuffer = new StringBuffer();
            // Storing input read-in data into string buffer in json format
            while ((inputString = dataIn.readLine()) != null) {
                strBuffer.append(inputString);
                //System.out.println("String Buffer: " + strBuffer.toString());
            }
            // Marshal JSON data into java object blIn
            BlockLedger blIn = gson.fromJson(strBuffer.toString(), BlockLedger.class);
            //System.out.println("BR Input: " + blIn.toString());
            System.out.println("Inserted in the priority blocking queue: " + blIn.getBc_Id() + "\n");
            // Place the java object - blIn (block record input) into concurrent priority queue
            // Each process has its own queue
            BlockchainToDo.blockChainQ.put(blIn);
            //System.out.println("Elements in queue: " + BlockchainToDo.blockChainQ);

            // closes the socket connection
            uvbSocket.close();
        } catch (Exception exception) {
            // exception handling
            exception.printStackTrace();
        }
    }
}

// Updated BlockChain Server class - In this implementation, our UBlockchainServer will receive blocks that
// are verified and then, updates each processes ledger. After connection to the socket has been established, the verified block
// is sent. It is read in using the JSON object.
class UBlockchainServer implements Runnable {
    @Override
    public void run() {
        // defined queue length - number of incoming request that can be stored in queue
        int Qsize = 8;
        // declaring blockChainSock variable
        Socket blockChainSock;
        // It will display a message announcing the launch of the updated blockchain server along with the port number
        System.out.println("Setting in motion the Blockchain Server thread using : " + Ports.blockChainPortServ);
        try {
            // creating a server socket instance that takes the port number for the blockchain server and the queue length
            ServerSocket serverSock = new ServerSocket(Ports.blockChainPortServ, Qsize);
            while (true) {
                blockChainSock = serverSock.accept();
                new UpgradedBlockchainWorker(blockChainSock).start();
            }
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }
}

// Worker class for updated blockchain server class
// Accepts verified blocks rather than the entire blockchain ledger.
// These verified blocks are then added to THIS process ledger
class UpgradedBlockchainWorker extends Thread {
    Socket blockChainSock;

   // a constructor declaration that assigns incoming socket data to a locally defined socket variable for further processing
    public UpgradedBlockchainWorker(Socket blockChainSock) {
        this.blockChainSock = blockChainSock;
    }

    @Override
    public void run() {
        try {
            // scanning data as input from blockchain inputSTream
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(blockChainSock.getInputStream()));
            Gson gson = new Gson();
            String buffData;
            StringBuffer dataBuffer = new StringBuffer();
            // storing records in JSON format
            while ((buffData = dataIn.readLine()) != null) {
                dataBuffer.append(buffData);
            }
            BlockLedger blockLedgerIn = gson.fromJson(dataBuffer.toString(), BlockLedger.class);

            // Verifying if the block is in the blockchain ledger or not, if not, adds the block to THIS process' copy of the ledger
            if (!BlockchainToDo.isMatching(blockLedgerIn)) {
                BlockchainToDo.verBlockList.add(0, blockLedgerIn);
                System.out.println("Verified block added to blockChain Ledger and count in the Ledger is: " + BlockchainToDo.verBlockList.size());

            }
           
            // the condition here is if pid is 0 then blockchain  record is written to disk by invoking the converttTOJSON method()
            if (BlockchainToDo.pId == 0) {
                BlockchainToDo.converttTOJSON();
            }
            blockChainSock.close(); // connection closed
        } catch (IOException iec) {
            iec.printStackTrace();
        }
    }
}

// BlockChain working class. As input to its constructor, we pass the priority queue of THIS process, which contains UVB. 
// The block is dequeued and stored in a temporary variable for further processing.
class HashPuzzle implements Runnable {

    // declaring local block Queue variable
    BlockingQueue<BlockLedger> blockChainQueue;

    // A group of alphanumeric strings - used to create our random string - our correct guess
    private static final String alphNumString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // a constructor declaration that enqueues and binds to a locally declared queue variable
    public HashPuzzle(PriorityBlockingQueue<BlockLedger> blockChainQ) {
        this.blockChainQueue = blockChainQ;
    }

    @Override
    public void run() {
        try {
            while (true) {
                BlockLedger blockLed = BlockchainToDo.blockChainQ.take();
                // str form will be used in msg digest hash
                String blockLedStr = blockLed.getBc_Id() + blockLed.getFName() +
                        blockLed.getLName() + blockLed.getSocSecNum() +
                        blockLed.getDOB() + blockLed.getMedicalDiagnosis() +
                        blockLed.getMedicalTreatment() + blockLed.getMedicalPrescription() +
                        blockLed.getProcessCreation();
                String randString;
                String conctString;
                String hashedString;

                boolean isHashAuthenticated;
                boolean isBlockIdAuthenticated;
                // this condition is to check if block is present in ledger already or not
                if (BlockchainToDo.isMatching(blockLed) && blockLed != null) {
                    continue;
                }

                // verification of Signatured BlockId
                isBlockIdAuthenticated = BlockchainToDo.verifySignature(blockLed.getBc_Id().getBytes(),
                        BlockchainToDo.pubKeyList[Integer.parseInt(blockLed.getProcessCreation())],
                        Base64.getDecoder().decode(blockLed.getSignatureBlock_ID()));

                String msgBlockk = isBlockIdAuthenticated ? "Block ID Signed" : "Block ID not Signed";
                System.out.println(msgBlockk);

                // Verification of the signed hash of the SHA256 data of the creator process
                isHashAuthenticated = BlockchainToDo.verifySignature(blockLed.getHashMaker().getBytes(),
                        BlockchainToDo.pubKeyList[Integer.parseInt(blockLed.getProcessCreation())],
                        Base64.getDecoder().decode(blockLed.getHashSignatureMaker()));

                String messageHash = isHashAuthenticated ? "Hash Signed" : "Hash not Signed";
                System.out.println(messageHash);

                // restoring the id of prev block
                String formerBlockId = BlockchainToDo.verBlockList.get(0).getBc_Id();
                // this is used to keep a check if puzzel is solved or not
                int thresholdNumber;
                // blocks are aaded to this once puzzel is solved
                String upgradedBlock = blockLedStr;
                upgradedBlock = upgradedBlock + BlockchainToDo.verBlockList.get(0).getVictoryHashValue();
                // allows to solve puzzle if the block record is not already a part of the ledger
                if (!BlockchainToDo.isMatching(blockLed)) {
                    try {

                        for (int k = 1; k < 20; k++) {
                            // this is for reciving a random alpha numeric value
                            randString = randomAlphaNumeric(8);
                            // concatinating values
                            conctString = upgradedBlock + randString;

                            // Getting hash value of our BlockData
                            MessageDigest md = MessageDigest.getInstance("SHA-256");
                            byte[] bytesHash = md.digest(conctString.getBytes(StandardCharsets.UTF_8));

                            // Converting into a string of hex values
                            hashedString = byteArray2Str(bytesHash);
                            System.out.println("Hash Value of the final BlockData is..... : " + hashedString);

                            thresholdNumber = Integer.parseInt(hashedString.substring(0, 4), 16);
                            System.out.println("First 16 bits in Hex and Decimal: " + hashedString.substring(0, 4) + " and " + thresholdNumber);

                            // if thresholdNumber is not less that 20k then, rework
                            if (!(thresholdNumber < 25000)) {
                                System.out.format("%d is not lesser than 25,000. Therefore solving the puzzel again...!\n\n", thresholdNumber);
                            }
                            // When we meet our threshold - puzzle solved
                            if (thresholdNumber < 25000) {
                                // Ensuring that previous block record (block ID) is same as the block id when we started
                                // this process i.e. making sure the block chain ledger has not be updated.
                                // If it has been updated,  then sending the block back for reverification
                                if (!formerBlockId.equals(BlockchainToDo.verBlockList.get(0).getBc_Id())) {
                                    System.out.println("Reading BlockData from Work Loop");
                                    BlockchainToDo.transferBlockToLedger(blockLed, "reVerBlockList");
                                }
                                // If Blockchain ledger has not been updated, then adding block to ledger
                                else {
                                    // Setting winning hash value for the current data block
                                    blockLed.setVictoryHashValue(hashedString);
                                    // Setting the random seed string for the current data block
                                    blockLed.setRandomSeedValue(randString);
                                    System.out.format("%d is less than 25,000. Puzzle Solved!\n", thresholdNumber);
                                    System.out.println("Winning Random Seed String: " + randString);
                                    // Setting previous blockData winning hash value to the current BlockData
                                    blockLed.setFormerHashVal(BlockchainToDo.verBlockList.get(0).getVictoryHashValue());

                                    // Getting previous block number; so that we can increment it and
                                    // set updated block number field for current block data
                                    int blockNum = Integer.parseInt(BlockchainToDo.verBlockList.get(0).getBlockNum());
                                    blockNum++;
                                    blockLed.setBlockNum(String.valueOf(blockNum));
                                    // Setting the process ID number that verified this BlockData
                                    blockLed.setProcessIdentificationVer(String.valueOf(BlockchainToDo.pId));

                                    //Used to sign the winning hash value with verifier digital signature
                                    String signHashVerifier;

                                    byte[] digitalSign = BlockchainToDo.signData(hashedString.getBytes(),
                                            BlockchainToDo.pubPriKeyPair.getPrivate());
                                    signHashVerifier = Base64.getEncoder().encodeToString(digitalSign);

                                    blockLed.setVictorySignaturedHashValue(signHashVerifier);

                                    // Finally, adding the signed Block to Ledger
                                    BlockchainToDo.verBlockList.add(0, blockLed);
                                    // Displaying message on the terminal
                                    System.out.println("Block added to Blockchain Ledger.");
                                    System.out.println("Verified Blocks count is: " + BlockchainToDo.verBlockList.size());

                                    // Multicasting the verified block to all the processes
                                    BlockchainToDo.transferBlockToLedger(blockLed, "verBlockListUpdate");
                                    continue;
                                }
                                break;
                            }
                            if (BlockchainToDo.isMatching(blockLed)) {
                                // Periodically checking if current record has already been verified or not.
                                // If it is, then abandoning this process's attempt to verify the block
                                //System.out.println("matching Block - Already verified!!");
                                break;
                            }
                            // Invoking sleep method
                            BlockchainToDo.exePause();
                        }
                    } catch (Exception excpt) {
                        // Exception handling
                        excpt.printStackTrace();
                    }
                }
            }
        } catch (Exception excpt) {
            excpt.printStackTrace();
        }
    }

    // Method that creates random seed string (alphanumeric) from count provided
    // Part of utility code provided by Prof. Elliott
    public static String randomAlphaNumeric(int count) {
        StringBuilder stringBuilder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * alphNumString.length());
            stringBuilder.append(alphNumString.charAt(character));
        }
        return stringBuilder.toString();
    }

    // Method that takes in byte array argument and converts to string format.
    // Part of utility code provided by Prof. Elliott
    public static String byteArray2Str(byte[] ba2s) {
        StringBuilder hexaDecimalString = new StringBuilder(ba2s.length * 2);
        for (byte byteA2S : ba2s) {
            hexaDecimalString.append(String.format("%02X", byteA2S));
        }
        return hexaDecimalString.toString();
    }
}