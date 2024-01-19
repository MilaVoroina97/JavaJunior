package org.example.seminar1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cart <T extends Food>{

    private final ArrayList<T> foodThings;
    private final UMarket uMarket;
    private final Class<T> clazz;

    /**
     * Создание нового экземпляра корзины
     * @param clazz - ссылка на описатель типа, для того чтобы определить какого типа корзина формируется
     * @param uMarket - ссылка на определенный магазин, т.е. корзина будет относится к какому - либо экземпляру
     *                магазина
     */

    public Cart(Class<T> clazz, UMarket uMarket){
        this.clazz = clazz;
        this.uMarket = uMarket;
        this.foodThings = new ArrayList<>();
    }

    public Collection<T> getFoodThings() {
        return foodThings;
    }

    public void printFoods(){
        AtomicInteger index = new AtomicInteger(1);
        foodThings.stream()
                .forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                        index.getAndIncrement(),food.getName(),
                        food.getProteins() ? "Да" : "Нет",
                        food.getFats() ? "Да" : "Нет",
                        food.getCarbohydrates() ? "Да" : "Нет"));
    }

    public void cardBalancing(){

/*        boolean proteins = false;
        boolean fats = false;
        boolean carbohydrates = false;

        // проходим по всем товарам в корзине
        for(var food : foodThings){
            // и как только появляется продукт с протеинами, меняется значение переменной proteins
            if(!proteins && food.getProteins()){
                proteins = true;
            }else if(!fats && food.getFats()){
                fats = true;
            }else if(!carbohydrates && food.getCarbohydrates()){
                carbohydrates = true;
            }if(proteins && fats && carbohydrates)
                break;
        }
        // если после прохода по всем товарам оказывается, что корзина имеет весь набо БЖУ, то выходим из метода
        if(proteins && fats && carbohydrates){
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        //если чего - то для балансировки в корзине не хватает, то обращаемся уже к списку продуктов в магазине,
        //для того чтобы их добавить по типу, с которым идет работа в текущем экземпляре класса корзины
        for (var thing : uMarket.getThings(clazz)){
            if(!proteins && thing.getProteins()){
                proteins = true;
                foodThings.add(thing);
            }else if (!fats && thing.getFats()){
                fats = true;
                foodThings.add(thing);
            }else if (!carbohydrates && thing.getCarbohydrates()){
                carbohydrates = true;
                foodThings.add(thing);
            }if (proteins && carbohydrates && fats)
                break;
        }
        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");*/

        AtomicBoolean proteins = new AtomicBoolean(foodThings.stream().anyMatch(Food::getProteins));
        AtomicBoolean fats = new AtomicBoolean(foodThings.stream().anyMatch(Food::getFats));
        AtomicBoolean carbohydrates = new AtomicBoolean(foodThings.stream().anyMatch(Food::getCarbohydrates));
        if(proteins.get() && fats.get() && carbohydrates.get()){
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        List<Food> additionalFood = uMarket.getThings(clazz).stream()
                .filter(thing -> (!proteins.get() && thing.getProteins()) || (!fats.get() && thing.getFats()) ||
                        (!carbohydrates.get() && thing.getCarbohydrates()))
                .peek(foodThings::add)
                .collect(Collectors.toList());

        additionalFood.forEach(thing ->{
                    proteins.set(proteins.get() || thing.getProteins());
                    fats.set(fats.get() || thing.getFats());
                    carbohydrates.set(carbohydrates.get() || thing.getCarbohydrates());
                });


        if (proteins.get() && fats.get() && carbohydrates.get()) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }

    /**
     * Проверка наличия конкретного питательного элемента в корзине
     * @param nutrientCheck список продуктов в корзине
     * @return состояние обновленного флага питательного элемента
     */
    private boolean checkNutrientFlag(Predicate<Food> nutrientCheck) {
        Optional<T> optionalFood = foodThings.stream()
                .filter(nutrientCheck)
                .findFirst();
        return optionalFood.isPresent();
    }

    /**
     * Поиск недостающих питательных элементов в корзине и добавление питательно элемента
     * исходя из общего фильтра продуктов
     * @param nutrientFlag наличие питательного элемента
     * @param nutrientCheck список продуктов в корзине
     * @param foods доступный список продуктов (исходя из текущего фильтра)
     * @return состояние обновленного флага питательного элемента (скорее всего будет true,
     * false - в случае, если невозможно найти продукт с нужным питательным элементом, в таком
     * случае, невозможно сбалансировать корзину по БЖУ
     */
    private boolean checkNutrientFlag(boolean nutrientFlag, Predicate<Food> nutrientCheck, Collection<T> foods) {
        if (!nutrientFlag) {
            Optional<T> optionalFood = foods.stream()
                    .filter(nutrientCheck)
                    .findFirst();
            optionalFood.ifPresent(foodThings::add);
            return optionalFood.isPresent();
        }
        return true;
    }



}
