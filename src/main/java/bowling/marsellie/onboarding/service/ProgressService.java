package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.Progress;
import bowling.marsellie.onboarding.repo.ProgressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressRepo progressRepo;

    public Progress createProgress(AppUser user) {
        return progressRepo.save(Progress.builder()
                .documentsPassed(Collections.emptySet())
                .user(user)
                .build());
    }
}
