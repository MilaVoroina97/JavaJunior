package org.example.seminar1;

import java.util.Scanner;

public class Program {

    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        UMarket market = new UMarket();
        System.out.println("Добро пожаловать в магазин U-Market");

        while (true){

            System.out.println("================================================================");
            System.out.println("0 - Завершение работы приложения");
            System.out.println("1 - Отобразить полный список товаров");
            System.out.println("2 - Сформировать онлайн корзину из снеков");
            System.out.println("3 - Сформировать онлайн корзину из полуфабрикатов");
            System.out.println("4 - Сформировать онлайн корзину из продуктов для приготовления");
            System.out.println("5 - Сформировать онлайн корзину из любых продовольственные товаров");
            System.out.println("Выберите пункт меню: ");

            if(scanner.hasNextInt()){
                int number = scanner.nextInt();
                scanner.nextLine();
                switch (number){
                    case 0 -> {
                        System.out.println("Завершение работы приложения.");
                        return;
                    }case 1 -> {
                        System.out.println("Список товаров:");
                        market.printThings(Thing.class);
                    }
                    case 2 -> createCart(Snack.class,market);
                    case 3 -> createCart(SemiFinishedFood.class,market);
                    case 4 -> createCart(HealthyFood.class,market);
                    case 5 -> createCart(Food.class,market);
                }

            }else{
                System.out.println("Некорректный пункт меню.\nПожалуйста, повторите попытку ввода.");
                scanner.nextLine();
            }

        }
    }

    public static <T extends Food> void createCart(Class<T> clazz, UMarket uMarket){

        Cart<T> cart = new Cart<>(clazz,uMarket);
        while (true){
            System.out.println("Список доступных товаров:");
            System.out.println("[0] Завершение формирования корзины + балансировка");
            uMarket.printThings(clazz);
            System.out.print("Укажите номер товара для добавления: ");
            if(scanner.hasNextInt()){

                int number = scanner.nextInt();
                scanner.nextLine();

                if(number == 0){
                    //cart.cartBalancing();
                    System.out.println("Ваша корзина содержит продукты:");
                    cart.printFoods();
                    return;
                }else {
                    T thing = uMarket.getThingByIndex(clazz,number);
                    if(thing == null){
                        System.out.println("Некорректный номер товара.\nПожалуйста, повторите попытку ввода.");
                        continue;
                    }
                    cart.getFoodThings().add(thing);
                    System.out.println("Товар успешно добавлен в корзину.");
                    System.out.println("Ваша корзина содержит продукты:");
                    cart.printFoods();
                }
            }else {
                System.out.println("Некорректный пункт меню.\nПожалуйста, повторите попытку ввода.");
                scanner.nextLine();
            }
        }
    }
}
