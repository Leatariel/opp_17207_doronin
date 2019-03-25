package ru.nsu.ccfit.doronin.lab2.Calculator.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.doronin.lab2.Calculator.CommandClass;
import ru.nsu.ccfit.doronin.lab2.Calculator.Context;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DivTest {

    private Context context;
    private List<String> arg;
    private CommandClass obj = null;

    @BeforeEach
    void setUp() {
        try {
            context = new Context();
            arg = new ArrayList<>();
            obj = new Div();
            context.pushToStack(4.0);
            context.pushToStack(4.0);
            arg.add("+");

            Class<?> className = Class.forName(obj.getClass().getName());

            //Получаю список полей класса и начинаю их обход
            Field[] declaredFields = className.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //Делаю поле доступным
                declaredField.setAccessible(true);
                //Если аннотация класса Inject, то
                if (declaredField.isAnnotationPresent(Inject.class)) {
                    //Получаю доступ к аннотации
                    Inject annotation = declaredField.getDeclaredAnnotation(Inject.class);
                    switch (annotation.arg()) {
                        case CONTEXT:
                            declaredField.set(obj, context);
                            break;
                        case ARGS:
                            declaredField.set(obj, arg);
                            break;

                    }
                }
            }
        }catch(Exception e){
            System.out.println("Ошибка в конструкторе теста: " + e);
        }
    }

    @Test
    void run() {
        try {
            assertNotNull(obj);
            obj.run();
            assertEquals(2.0, context.popFromStack());
        }catch(Exception e){
            System.out.println("Ошибка во время тестирования: " + e);
        }
    }
}