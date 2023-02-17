package bowling.marsellie.onboarding.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;

@RequiredArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    NEWCOMER("Новый сотрудник"),
    HR("HR"),
    DEPARTMENT_HEAD("Руководитель");

    private final String name;

    @Override
    public String getAuthority() {
        return "ROLE_" + this;
    }
}
