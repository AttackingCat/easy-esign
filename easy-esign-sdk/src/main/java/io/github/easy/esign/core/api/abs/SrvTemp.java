package io.github.easy.esign.core.api.abs;

import io.github.easy.esign.core.BaseExecute;
import io.github.easy.esign.core.ESignManager;

public abstract class SrvTemp {
    public static <T extends SrvTemp> BaseExecute getExecute(Class<T> clazz) {
        return ESignManager.getContext(clazz);
    }
}
