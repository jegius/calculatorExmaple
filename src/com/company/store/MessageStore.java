package com.company.store;
import com.company.enums.OperationType;
import com.company.enums.Validation;
import com.company.pojo.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageStore {

    private static MessageStore instance;
    private HashMap<OperationType, List<Operation>> operationMap = new HashMap<>();

    public static MessageStore getInstance() {
        if (instance == null) {
            instance = new MessageStore();
        }
        return instance;
    }

    public List<Operation> getOperationsByType(OperationType operationType) {
        return operationMap.get(operationType);
    }

    private MessageStore() {
        initStore();
    }

    private void initStore() {
        List<Operation> sqrtOperations = new ArrayList<>();
        sqrtOperations.add(new Operation("Введите число для возведения в степень", Validation.POSITIVE_NUMBER));

        operationMap.put(OperationType.SQRT, sqrtOperations);

        List<Operation> sumOperation = new ArrayList<>();
        sumOperation.add(new Operation("Введите первое число для вычисления суммы", Validation.NUMBER));
        sumOperation.add(new Operation("Введите второе число для вычисления суммы", Validation.NUMBER));

        operationMap.put(OperationType.SUM, sumOperation);

        List<Operation> multiSumOperation = new ArrayList<>();

        multiSumOperation.add(new Operation("Введите десять положительный цифр для вычисления суммы", Validation.NUMBER));
        multiSumOperation.add(new Operation(null, Validation.NUMBER));
        multiSumOperation.add(new Operation(null, Validation.NUMBER));
        multiSumOperation.add(new Operation(null, Validation.NUMBER));
        multiSumOperation.add(new Operation("Следующее число не может ровнятся нулю", Validation.NO_ZERO));
        multiSumOperation.add(new Operation(null, Validation.NUMBER));
        multiSumOperation.add(new Operation("Следующее число не должно быть отрицательным", Validation.POSITIVE_NUMBER));
        multiSumOperation.add(new Operation(null, Validation.NUMBER));
        multiSumOperation.add(new Operation(null, Validation.NUMBER));
        multiSumOperation.add(new Operation(null, Validation.NUMBER));

        operationMap.put(OperationType.MULTISUM, multiSumOperation);


    }
}