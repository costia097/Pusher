package main.dtos;

public enum TestEnum {
    first {
        @Override
        protected void method(InnerEnum innerEnum) {

        }
    },
    second {
        @Override
        protected void method(InnerEnum innerEnum) {

        }
    };

    protected abstract void method(InnerEnum innerEnum);
}
