package io.github.easy.esign.core.api.abs;

import io.github.easy.esign.core.Execute;
import io.github.easy.esign.core.Manager;

public abstract class SrvTemp {
    public static <T extends SrvTemp> Execute execute() {
        return Manager.getExecute();
    }
}
