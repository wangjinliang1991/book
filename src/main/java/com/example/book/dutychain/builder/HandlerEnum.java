package com.example.book.dutychain.builder;

import com.example.book.dutychain.AbstractBusinessHandler;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/25
 */
public enum HandlerEnum {
    city("com.example.book.dutychain.CityHandler"),
    sex("com.example.book.dutychain.SexHandler"),
    product("com.example.book.dutychain.ProductHandler");
    String value = "";

     HandlerEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    /**
     * @author wangjl
     * @date 2024/8/25
     */
    @Component
    public static class HandlerFactory {

        public AbstractBusinessHandler getHandler(String handlerType){

            HandlerEnum handlerEnum = handlerType.equals("city")? city :
                    handlerType.equals("sex")? sex :
                            handlerType.equals("product")? product :
                                    null;
            if(handlerEnum == null){
                throw new UnsupportedOperationException("handlerType not found");
            }
            try {
                return  (AbstractBusinessHandler) Class.forName(handlerEnum.getValue()).newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
