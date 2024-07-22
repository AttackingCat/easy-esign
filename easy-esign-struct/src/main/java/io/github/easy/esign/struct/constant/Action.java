package io.github.easy.esign.struct.constant;

import io.github.easy.esign.struct.auth.notify.AuthPass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Action {

    AUTH_PASS("AUTH_PASS", AuthPass.class),

    AUTHORIZE_FINISH("AUTHORIZE_FINISH", null),

    AUTHORIZE_CHANGE("AUTHORIZE_CHANGE", null);

    private final String action;
    private final Class<?> clazz;

    public static Action getByAction(String action) {
        for (Action value : values()) {
            if (value.action.equals(action)) {
                return value;
            }
        }
        return null;
    }
}
