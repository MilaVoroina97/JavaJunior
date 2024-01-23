package org.example.homework.two;

import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {

    /**
     * Создание запроса на добавление информации в БД
     */

    public String buildInsertQuery(Object object) throws IllegalAccessException {

        Class<?> clazz = object.getClass();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        if(clazz.isAnnotationPresent(Table.class)){
            //если в классе присутствует искомая аннотация, то мы можем получить доступ к этой аннотации
            // и поработать с методами, которые в рамках этой аннотации были имплементированы
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name())
                    .append(" (");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                if(field.isAnnotationPresent(Column.class)){
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    query.append(columnAnnotation.name())
                            .append(", ");
                }
            }
            changeSingsInQuery(query);
            query.append(") VALUES (");

            for (Field field : fields){
                if(field.isAnnotationPresent(Column.class)){
                    field.setAccessible(true);
                    query.append("'")
                            .append(field.get(object))
                            .append("', ");
                }
            }

            changeSingsInQuery(query);
            query.append(")");
        }else {
            return "";
        }
        return query.toString();
    }

    /**
     * Создание запроса на поиск данных в БД
     * @return строку с набором полей в рамках одно объекта типа Employee, id которого известен
     */

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey){

        StringBuilder query = new StringBuilder("SELECT * FROM ");
        if(clazz.isAnnotationPresent(Table.class)){
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name())
                    .append(" WHERE ");
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if(field.isAnnotationPresent(Column.class)){
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if(columnAnnotation.primaryKey()){
                        query.append(columnAnnotation.name())
                                .append(" = '")
                                .append(primaryKey)
                                .append("'");
                        break;
                    }
                }
            }
        }else{
            return "";
        }

        return query.toString();

    }
    /**
     * Создание запроса на обновление / изменение данных в БД
     */

    public String buildUpdateQuery(Object object) throws IllegalAccessException {

        Class<?> clazz = object.getClass();
        StringBuilder query = new StringBuilder("UPDATE ");
        if (clazz.isAnnotationPresent(Table.class)){
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name())
                    .append(" SET ");
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if(field.isAnnotationPresent(Column.class)){
                    //обновляем только те поля, которые не являются ключом у конкретного объекта, переданного
                    // в качестве аргумента функции
                    field.setAccessible(true);
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    //если в процессе перебора полей встретился первичный ключ, то тогда текущая итерация
                    // пропускается (с помощью continue) и идет переход к следующей итерации
                    if(columnAnnotation.primaryKey()){
                        continue;
                    }
                    query.append(columnAnnotation.name())
                            .append(" = '")
                            .append(field.get(object))
                            .append("', ");
                }
            }

            changeSingsInQuery(query);
            query.append(" WHERE ");


            for(Field field : fields){
                if(field.isAnnotationPresent(Column.class)){

                    Column columnAnnotation = field.getAnnotation(Column.class);
                    //здесь работаем только с первичным ключом и по этому ключу дополняем запрос
                    if(columnAnnotation.primaryKey()){
                        query.append(columnAnnotation.name())
                                .append(" = '")
                                .append(field.get(object))
                                .append("'");
                    }
                }
            }
        }else{
            return "";
        }

        return query.toString();
    }

    private  void changeSingsInQuery(StringBuilder query){

        if(query.charAt(query.length() - 2) == ','){
            query.delete(query.length() - 2, query.length());
        }

    }


}
