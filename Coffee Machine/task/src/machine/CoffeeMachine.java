package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private Scanner scanner;
    private int money;
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private boolean stopped;

    private CoffeeMachine() {
        this.scanner = new Scanner(System.in);
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
        this.stopped=false;
        }

    public static void main(String[] args) {
        CoffeeMachine coffeemachine = new CoffeeMachine();

        while (!coffeemachine.stopped) {
            coffeemachine.processAction();
        }
    }

    private void processAction() {
        System.out.print("write action (buy, fill, take, remaining, exit): ");

        switch (scanner.next()) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                printContents();
                break;
            case "exit":
                stopped=true;
            default:
                break;
        }
    }

    private void printContents() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }

    private void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void fill() {
        System.out.print("Write how many ml of water do you want to add: ");
        water += scanner.nextInt();

        System.out.print("Write how many ml of milk do you want to add: ");
        milk += scanner.nextInt();

        System.out.print("Write how many grams of coffee beans do you want to add: ");
        beans += scanner.nextInt();

        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        cups += scanner.nextInt();
    }

    private void buy() {
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String option = scanner.next();

        int needWater = 0;
        int needMilk = 0;
        int needBeans = 0;

        switch (option) {
            case "1":
                needWater = 250;
                needBeans = 16;
                money += 4;
                break;
            case "2":
                needWater = 350;
                needMilk = 75;
                needBeans = 20;
                money += 7;
                break;
            case "3":
                needWater = 200;
                needMilk = 100;
                needBeans = 12;
                money += 6;
                break;
            case "back":
            default:
                return;
        }
        if (hasEnoughResources(needWater, needMilk, needBeans)) {
            water -= needWater;
            milk -= needMilk;
            beans -= needBeans;
            cups--;
        }

    }

    private boolean hasEnoughResources(int needWater, int needMilk, int needBeans) {
        if (water < needWater) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (milk < needMilk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (beans < needBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }

    }
}