package com.example.demo.study.enumstudy;

public class VendingMachine {

    private static State state = State.RESTING;

    private static int amount = 0;

    private static Input selection = null;


    enum StateDuration{TRANSIENT}

    enum State{
        RESTING {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY{
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            System.out.println("钱不够 " + selection);
                        else
                            state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT){
            @Override
            void next() {
                System.out.println("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT){
            @Override
            void next() {
                if (amount > 0) {
                    System.out.println("你的零钱" + amount);
                    amount = 0;
                }
                state = RESTING;
            }

        },
        TERMINAL{
            @Override
            void output() {
                System.out.println("halted");
            }
        }
        ;
        private boolean isTransient = false;
        State() {}
        State(StateDuration trans){
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("call parent next input");
        }

        void next() {
            throw new RuntimeException("call parent next");
        }

        void output() {
            System.out.println(amount);
        }
    }

    static void run(Generator<Input> generator) {
        while (state != State.TERMINAL) {
            state.next(generator.next());
            while (state.isTransient) {
                state.next();
            }
            state.output();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
        run(gen);

    }
}
