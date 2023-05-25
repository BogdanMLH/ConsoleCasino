package Projects;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Casino {
    public static void main(String[] args) {
        clearConsole();
        System.out.println("Choose language\n");
        System.out.println("English / Russian");
        Scanner scanner = new Scanner(System.in);
        String lang = readString(scanner);

        switch (lang) {
            case "e" -> mainEn();
            case "r" -> mainRu();
        }
    }

    //         EN-lang casino version
    public static void mainEn(){
        clearConsole();
        //-----START CASINO PROGRAM-----
        System.out.println("""
                -----------------Welcome to our game place!-----------------
                \tWe are hope you will have a great time playing here!\t
                """);

        //REGISTRATION
        System.out.println("[ Create your account ]");
        User player = new User();
        Scanner scanner = new Scanner(System.in);
        String input;
        double bal = 0;
        boolean valid = false;
        //set name
        do {
            System.out.print("Set your player name (30 symbols max): ");
            input = scanner.nextLine();
            player.changeName(input);
        } while (input.length() > 30);
        //set balance
        while(!valid){
            try {
                System.out.print("Set your balance: ");
                bal = scanner.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Only numbers!");
                scanner.nextLine();
            }
        }

        player.changeBalance(bal);
        while(bal > 10000 || bal < 0){
            System.out.println("<<⚠ Balance can't be more than 10 000 or less than 0 >>");
            System.out.print("Set your balance: ");
            bal = scanner.nextDouble();
            player.changeBalance(bal);
        }
        clearConsole();
        System.out.println("\t✅Congratulations! You are now registered!✅\n\n");
        sleep(3);
        clearConsole();
        //Registration end


        //--------------------------------WORKING PROGRAM---------------------------------------//

        while(true){                    // Working until exit won't make return
            clearConsole();
            System.out.println("|---------------------------------------|");
            System.out.println("|\t\t\uD83C\uDFB0\uD83D\uDD25\uD83D\uDC79HELL CASINO\uD83D\uDC79\uD83D\uDD25\uD83C\uDFB0\t\t|");
            System.out.println("|---------------------------------------|\n");
            System.out.println("\tWhat game you want to play today?\n");
            System.out.println("1. \uD83D\uDD25Hot Slots!\uD83D\uDD25");
            System.out.println("2. Account");
            System.out.println("3. Exit");
            System.out.println();

            int pick;
            do{
                pick = inputWithCheck("e");
            }while(!(pick == 1 || pick == 2 || pick == 3));

            boolean inAction = true;

            switch (pick) {  //Picking an option
                case 1 -> {
                    clearConsole();
                    System.out.println("\uD83D\uDD25\uD83D\uDD25Welcome to Hot Slots!\uD83D\uDD25\uD83D\uDD25\n");
                    System.out.println("""
                            Hot Slots - is the most popular\s
                            game in our casino.
                            Try your luck and win up\s
                            to 2000% of your initial bet!""");
                    System.out.println();
                    while(inAction){
                        boolean playing = true;
                        System.out.println("\uD83C\uDFB0Wanna play?\uD83C\uDFB0 \n");
                        System.out.println("""
                                1. \uD83D\uDD25Play Hot Slots\uD83D\uDD25\s
                                2. Rules
                                3. Coefficients\s
                                4. Exit
                                """);
                        int pick1 = inputWithCheck("e");

                        switch (pick1){
                            case 1 -> {
                                while(playing){
                                    clearConsole();
                                    double currentBalance = player.getBalance();
                                    System.out.println("\uD83D\uDD25\uD83D\uDD25HOT SLOTS\uD83D\uDD25\uD83D\uDD25");
                                    System.out.println();
                                    System.out.println("Your balance: " + currentBalance + "\uD83D\uDCB0");
                                    if(player.getBalance() > 50){
                                        double bet;                                  //Bet check
                                        while(true){
                                            try {
                                                System.out.print("Place a bet (50 minimum)\uD83D\uDCB5: ");
                                                bet = scanner.nextDouble();
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Only numbers!");
                                                scanner.nextLine();
                                            }
                                        }
                                        while (bet < 50 || bet > currentBalance){
                                            System.out.print("⚠Bet must be more than 50 and no more than your balance: ");
                                            bet = scanner.nextDouble();
                                        }

                                        System.out.println("Coefficients: ");
                                        System.out.println("""
                                                🍋🍋🍋 - x1.5
                                                🍊🍊🍊🍊 - x3
                                                🍇🍇🍇🍇🍇 - x10
                                                
                                                🍋🍋 + 🍇🍇 = x2
                                                
                                                🍒🍒 - x2
                                                🍒🍒🍒 - x3
                                                🍒🍒🍒🍒 - x10
                                                🍒🍒🍒🍒🍒 - x20
                                                """);
                                        sleep(2);
                                        System.out.println("Your bet: " + bet);
                                        currentBalance -= bet;
                                        sleep(2);

                                        //                   SPINNING SLOT
                                        System.out.println("|-------------------------|");
                                        double win = Slots.spinning(bet);
                                        System.out.println();
                                        System.out.println("|-------------------------|");

                                        if(win > 0){
                                            System.out.println("\uD83D\uDC4BCongratulations! Your winning bet is: " + win + "\uD83D\uDC4B");
                                            player.changeBalance(currentBalance + win);
                                        }else{
                                            System.out.println("\uD83D\uDE21That was close! But it's not over yet!\uD83D\uDE21");
                                            player.changeBalance(currentBalance);
                                        }
                                        System.out.println();
                                        System.out.println("Do you want to play again?\uD83C\uDFB0");
                                        System.out.println("""
                                                1. \uD83D\uDD25Play in Hot Slots!\uD83D\uDD25
                                                2. Exit
                                                """);
                                        int input1 = inputWithCheck("e");
                                        switch (input1){
                                            case 1: break;
                                            case 2: playing = false;
                                        }    //Leaving or playing again
                                        clearConsole();
                                    }else{
                                        System.out.println("⚠Sorry, but you don't have enough credit to play Hot Slots!⚠");
                                        sleep(5);
                                        playing = false;
                                        clearConsole();
                                    }
                                }
                            }    //Play Hot Slots
                            case 2 -> {
                                clearConsole();
                                System.out.println("""
                                            To play Hot Slots you place a bet (minimum 50)
                                        and spin the slot. 5 different fruits can drop from
                                        5 slots: cherries, oranges, grapes, lemons and plums.
                                        When the same fruits fall out, the winning coefficient increases.
                                        For falling cherries, the coefficients are higher than
                                        for other fruits.
                                        
                                                            \uD83D\uDD25Play and win!\uD83D\uDD25
                                        """);
                                System.out.print("[press Enter for exit...]");
                                scanner.nextLine();
                                clearConsole();
                            }    //Rules
                            case 3 -> {
                                clearConsole();
                                System.out.println("Coefficients: ");
                                System.out.println("""
                                        All fruits (except cherry) 🍋🍊🍇🍑:
                                            3 - 150% of the bet (x 1.5)
                                            4 - 300% of the bet (x 3)
                                            5 - Big Win! = 1000% of the bet (x 10)
                                        Pairs of 2 different fruits (except cherri) gives 200% of the bet! (x 2)
                                        
                                        Cherry🍒:
                                            2 - Jackpot! = 200% of the bet (x 2)
                                            3 - BIG Jackpot! = 300% of the bet (x 3)
                                            4 - MONSTER Jackpot! = 1000% of the bet (x 10)
                                            5 - HOT-JACKPOT! = 2000% of the bet (x 20)
                                        """);
                                System.out.print("[press Enter for exit...]");
                                scanner.nextLine();
                                clearConsole();
                            }    //Coefficients
                            case 4 -> {
                                inAction = false;
                                clearConsole();
                            }    //Exit
                        }
                    }
                }    //Slots option
                case 2 -> {
                    while (inAction) {
                        clearConsole();
                        System.out.println("[Your Account]");
                        System.out.println("Name: " + player.getName());
                        System.out.println("Balance: " + player.getBalance());
                        System.out.println();
                        System.out.println("What do you wanna change? ");
                        System.out.println("""
                                1. Change name\s
                                2. Add balance
                                3. Back to menu
                                """);
                        int pick2 = inputWithCheck("e");

                        switch (pick2) {      //Picking an option about changing an account
                            case 1 -> {       //Change name
                                clearConsole();
                                String name;
                                do {
                                    System.out.print("Set your player name (30 symbols max): ");
                                    name = scanner.nextLine();
                                    player.changeName(name);
                                } while (name.length() > 30);
                                clearConsole();
                            }   //Change name
                            case 2 -> {       //Change balance
                                clearConsole();
                                bal = player.getBalance();
                                double dept = 0;
                                valid = false;
                                while(!valid || bal > 10000 || dept < 50 || dept+bal > 10000){
                                    try {
                                        System.out.println("<<⚠ Balance can't be more than 10 000>>\n");
                                        System.out.println("Your balance: " + bal);
                                        System.out.println("[Minimum 50]");
                                        System.out.print("Enter amount: ");
                                        dept = scanner.nextDouble();
                                        valid = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Only numbers!");
                                        scanner.nextLine();
                                    }
                                }
                                player.changeBalance(bal + dept);
                                clearConsole();
                            }   //Change balance
                            case 3 -> {         //Exit
                                inAction = false;
                                clearConsole();
                            }   //Exit
                        }
                        clearConsole();
                    }
                }    //Account option
                case 3 -> {
                    clearConsole();
                    System.out.println("\t\t\uD83D\uDD25Thank you for playing! Come back later!\uD83D\uDD25");
                    sleep(3);
                    return;
                }    //Exit
            }
        }

    }

    //         RU-lang casino version
    public static void mainRu(){
        clearConsole();
        //-----START CASINO PROGRAM-----
        System.out.println("""
                -----------------Добро пожаловать на нашу игровую площадку!-----------------
                \t\tМы надеемся, что вы отлично проведете время, играя здесь!\t
                """);

        //REGISTRATION
        System.out.println("[ Создайте аккаунт ]");
        User player = new User();
        Scanner scanner = new Scanner(System.in);
        String input;
        double bal = 0;
        boolean valid = false;
        //set name
        do {
            System.out.print("Установите имя игрока (максимум 30 символов): ");
            input = scanner.nextLine();
            player.changeName(input);
        } while (input.length() > 30);
        //set balance
        while(!valid){
            try {
                System.out.print("Пополните свой баланс: ");
                bal = scanner.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Только цифры!");
                scanner.nextLine();
            }
        }

        player.changeBalance(bal);
        while(bal > 10000 || bal < 0){
            System.out.println("<<⚠ Баланс не может быть больше 10 000 или меньше 0 >>");
            System.out.print("Пополните свой баланс: ");
            bal = scanner.nextDouble();
            player.changeBalance(bal);
        }
        clearConsole();
        System.out.println("\t✅Поздравляем! Вы зарегистрированы!✅\n\n");
        sleep(3);
        clearConsole();
        //Registration end


        //--------------------------------WORKING PROGRAM---------------------------------------//

        while(true){                    // Working until exit won't make return
            clearConsole();
            System.out.println("|---------------------------------------|");
            System.out.println("|\t\t\uD83C\uDFB0\uD83D\uDD25\uD83D\uDC79HELL CASINO\uD83D\uDC79\uD83D\uDD25\uD83C\uDFB0\t\t|");
            System.out.println("|---------------------------------------|\n");
            System.out.println("\tВо что сыграем сегодня?\n");
            System.out.println("1. \uD83D\uDD25Hot Slots!\uD83D\uDD25");
            System.out.println("2. Аккаунт");
            System.out.println("3. Выход");
            System.out.println();

            int pick;
            do{
                pick = inputWithCheck("r");
            }while(!(pick == 1 || pick == 2 || pick == 3));

            boolean inAction = true;

            switch (pick) {  //Picking an option
                case 1 -> {
                    clearConsole();
                    System.out.println("\uD83D\uDD25\uD83D\uDD25Добро пожаловать в Hot Slots!\uD83D\uDD25\uD83D\uDD25\n");
                    System.out.println("""
                            Hot Slots - самая популярная игра в нашем казино. \s
                            Испытайте свою удачу и выиграйте до
                            2000% от Вашей первоначальной ставки!
                            """);
                    while(inAction){
                        boolean playing = true;
                        System.out.println("\uD83C\uDFB0Хотите сыграть?\uD83C\uDFB0 \n");
                        System.out.println("""
                                1. \uD83D\uDD25Играть в Hot Slots\uD83D\uDD25\s
                                2. Правила
                                3. Коэффициенты\s
                                4. Назад в меню
                                """);
                        int pick1 = inputWithCheck("r");

                        switch (pick1){
                            case 1 -> {
                                while(playing){
                                    clearConsole();
                                    double currentBalance = player.getBalance();
                                    System.out.println("\uD83D\uDD25\uD83D\uDD25HOT SLOTS\uD83D\uDD25\uD83D\uDD25");
                                    System.out.println();
                                    System.out.println("Ваш баланс: " + currentBalance + "\uD83D\uDCB0");
                                    if(player.getBalance() > 50){
                                        double bet;                                  //Bet check
                                        while(true){
                                            try {
                                                System.out.print("Сделать ставку (минимум 50)\uD83D\uDCB5: ");
                                                bet = scanner.nextDouble();
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Только цифры!");
                                                scanner.nextLine();
                                            }
                                        }
                                        while (bet < 50 || bet > currentBalance){
                                            System.out.print("⚠Ставка должна быть больше 50 и не больше вашего баланса: ");
                                            bet = scanner.nextDouble();
                                        }

                                        System.out.println("Коэффициенты: ");
                                        System.out.println("""
                                                🍋🍋🍋 - x1.5
                                                🍊🍊🍊🍊 - x3
                                                🍇🍇🍇🍇🍇 - x10
                                                
                                                🍋🍋 + 🍇🍇 = x2
                                                
                                                🍒🍒 - x2
                                                🍒🍒🍒 - x3
                                                🍒🍒🍒🍒 - x10
                                                🍒🍒🍒🍒🍒 - x20
                                                """);
                                        sleep(2);
                                        System.out.println("Ваша ставка: " + bet);
                                        currentBalance -= bet;
                                        sleep(2);

                                        //                   SPINNING SLOT
                                        System.out.println("|-------------------------|");
                                        int[] nums = {1,2,3,4,5};
                                        int[] probabilities = {2, 5, 5, 5, 5};

                                        double win = Slots.spinning(bet);
                                        System.out.println();
                                        System.out.println("|-------------------------|");

                                        if(win > 0){
                                            System.out.println("\uD83D\uDC4BПоздравляем! Ваша выигрышная ставка: " + win + "\uD83D\uDC4B");
                                            player.changeBalance(currentBalance + win);
                                        }else{
                                            System.out.println("\uD83D\uDE21Это было близко! Но это еще не конец!\uD83D\uDE21");
                                            player.changeBalance(currentBalance);
                                        }
                                        System.out.println();
                                        System.out.println("Вы хотите играть снова?\uD83C\uDFB0");
                                        System.out.println("""
                                                1. \uD83D\uDD25Играть в Hot Slots!\uD83D\uDD25
                                                2. Выход
                                                """);
                                        int input1 = inputWithCheck("r");
                                        switch (input1){
                                            case 1: break;
                                            case 2: playing = false;
                                        }    //Leaving or playing again
                                        clearConsole();
                                    }else{
                                        System.out.println("⚠Извините, но у вас недостаточно средств для игры в Hot Slots!⚠");
                                        sleep(5);
                                        playing = false;
                                        clearConsole();
                                    }
                                }
                            }    //Play Hot Slots
                            case 2 -> {
                                clearConsole();
                                System.out.println("""
                                            Для игры в Hot Slots, вы делаете ставку (минимум 50)
                                        и вращаете слот. 5 разных фруктов могут выпасть из
                                        5 слотов: вишни, апельсины, виноград, лимоны и сливы.
                                        Когда выпадают одинаковые фрукты, коэффициент выигрыша увеличивается.
                                        Для вишен коэффициенты выше, чем для других фруктов.
                                        
                                                            \uD83D\uDD25Играй и выигрывай!\uD83D\uDD25
                                        """);
                                System.out.print("[нажмите Enter для выхода...]");
                                scanner.nextLine();
                                clearConsole();
                            }    //Rules
                            case 3 -> {
                                clearConsole();
                                System.out.println("Коэффициенты: ");
                                System.out.println("""
                                        Все фрукты (кроме вишни) 🍋🍊🍇🍑:
                                             3 - 150% от ставки (х 1.5)
                                             4 - 300% от ставки (х 3)
                                             5 - Big Win! = 1000% ставки (х 10)
                                        Пары из 2-х разных фруктов (кроме вишни) дают 200% ставки! (x 2)
                                             
                                        Вишня🍒:
                                             2 - Jackpot! = 200% от ставки (x 2)
                                             3 - BIG Jackpot! = 300% от ставки (х 3)
                                             4 - MONSTER Jackpot! = 1000% ставки (х 10)
                                             5 - HOT-JACKPOT! = 2000% ставки (х 20)
                                        """);
                                System.out.print("[нажмите Enter для выхода...]");
                                scanner.nextLine();
                                clearConsole();
                            }    //Coefficients
                            case 4 -> {
                                inAction = false;
                                clearConsole();
                            }    //Exit
                        }
                    }
                }    //Slots option
                case 2 -> {
                    while (inAction) {
                        clearConsole();
                        System.out.println("[Ваш аккаунт]");
                        System.out.println("Имя: " + player.getName());
                        System.out.println("Баланс: " + player.getBalance());
                        System.out.println();
                        System.out.println("Что ты хочешь изменить? ");
                        System.out.println("""
                                1. Изменить имя
                                2. Пополнить баланс
                                3. Вернуться в меню
                                """);
                        int pick2 = inputWithCheck("r");

                        switch (pick2) {      //Picking an option about changing an account
                            case 1 -> {       //Change name
                                clearConsole();
                                String name;
                                do {
                                    System.out.print("Установите имя игрока (максимум 30 символов): ");
                                    name = scanner.nextLine();
                                    player.changeName(name);
                                } while (name.length() > 30);
                                clearConsole();
                            }   //Change name
                            case 2 -> {       //Change balance
                                clearConsole();
                                bal = player.getBalance();
                                double dept = 0;
                                valid = false;
                                while(!valid || bal > 10000 || dept < 50 || dept+bal > 10000){
                                    try {
                                        System.out.println("<<⚠ Баланс не может быть больше 10 000>>\n");
                                        System.out.println("Ваш баланс: " + bal);
                                        System.out.println("[Пополнение минимум на 50]");
                                        System.out.print("Введите сумму: ");
                                        dept = scanner.nextDouble();
                                        valid = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Только цифры!");
                                        scanner.nextLine();
                                    }
                                }
                                player.changeBalance(bal + dept);
                                clearConsole();
                            }   //Change balance
                            case 3 -> {         //Exit
                                inAction = false;
                                clearConsole();
                            }   //Exit
                        }
                        clearConsole();
                    }
                }    //Account option
                case 3 -> {
                    clearConsole();
                    System.out.println("\t\t\uD83D\uDD25Спасибо за игру! Возвращайтесь и выигрывайте еще!\uD83D\uDD25");
                    sleep(3);
                    return;
                }    //Exit
            }
        }
    }


    //         Check methods
    private static String readString(Scanner scan) {
        System.out.print("[Enter e/r]: ");
        while (!(scan.hasNext("e") || scan.hasNext("r"))) {
            System.out.print("[Please, enter e/r]: ");
            scan.nextLine();
        }

        return scan.nextLine();
    }

    private static int inputWithCheck(String lang){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                if (Objects.equals(lang, "e")){
                    System.out.print("[Enter number]: ");
                } else if (Objects.equals(lang, "r")){
                    System.out.print("[Введите цифру]: ");
                }

                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Only numbers!");
                scanner.nextLine();
            }
        }
    }

    private static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void sleep(long n){
        try {
            Thread.sleep(n * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Slots extends Casino{
    private static int[] nums = {1,2,3,4,5};
    private static int[] probabilities = {2, 5, 5, 5, 5};

    public static double spinning(double bet){
        int[] nums = {1,2,3,4,5};
        int[] probabilities = {2, 5, 5, 5, 5};

        int[] drops = new int[nums.length]; // cherries, lemons, oranges, grapes, plums
        int i = 0;
        System.out.print("\t");
        while(i < 5){
            int randNum = generateNum();
            switch (randNum) {
                case 1 -> {
                    sleep(2);
                    System.out.print("\uD83C\uDF52\t"); //cherry
                    drops[0] += 1;
                }
                case 2 -> {
                    sleep(2);
                    System.out.print("\uD83C\uDF4B\t"); //lemon
                    drops[1] += 1;
                }
                case 3 -> {
                    sleep(2);
                    System.out.print("\uD83C\uDF4A\t"); //orange
                    drops[2] += 1;
                }
                case 4 -> {
                    sleep(2);
                    System.out.print("\uD83C\uDF47\t"); //grapes
                    drops[3] += 1;
                }
                case 5 -> {
                    sleep(2);
                    System.out.print("\uD83C\uDF51\t"); //plums
                    drops[4] += 1;
                }
            }
            i+=1;
            if (randNum>1)
                probabilities[randNum-1] -= 1;
        }        //Generate winning array

        if(drops[0] > 1){
            switch (drops[0]){
                case 2 -> {
                    return bet * 2;
                }
                case 3 -> {
                    return bet * 3;
                }
                case 4 -> {
                    return bet * 10;
                }
                case 5 -> {
                    return bet * 20;
                }
            }
        }    //Return winning based on coefficient (cherri)

        int doubles = 0;
        for(int j = 1; j < drops.length; j++){
            if(drops[j] > 1){
                switch (drops[j]){
                    case 3 -> {
                        return bet * 1.5;
                    }
                    case 4 -> {
                        return bet * 3.0;
                    }
                    case 5 -> {
                        return bet * 10;
                    }
                    case 2 -> {
                        doubles += 1;
                    }
                }
            }
        }    //Return winning based on coefficient (except cherri)

        if(doubles > 1)
            return bet * 2;

        return 0;
    }

    public static int generateNum(){
        int n = 0;
        int[] array = new int[Arrays.stream(probabilities).sum()];
        for(int i = 0; i < probabilities.length; i++) {
            int j = probabilities[i];
            for (; j > 0; j--) {
                array[n] = nums[i];
                n++;
            }
        }
        return array[(int)(Math.random() * (array.length))];
    }
}

class User{
    private String name;
    private double balance;

    public void changeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void changeBalance(double balance) {
        this.balance = balance;
    }
}