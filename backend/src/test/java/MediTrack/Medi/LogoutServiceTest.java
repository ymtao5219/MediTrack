package MediTrack.Medi;

import MediTrack.Medi.repository.TokenRepository;
import MediTrack.Medi.service.LogoutService;
import MediTrack.Medi.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogoutServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private LogoutService logoutService;

    @Test
    void logout_WithValidToken_TokenIsInvalidated() {
        String jwt = "valid.jwt.token";
        Token storedToken = new Token(); // Replace with actual token creation logic
        storedToken.setToken(jwt);
        // Other token setup if necessary

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(tokenRepository.findByToken(jwt)).thenReturn(Optional.of(storedToken));

        logoutService.logout(request, response, authentication);

        verify(tokenRepository).save(storedToken);
        assertTrue(storedToken.isExpired());
        assertTrue(storedToken.isRevoked());
    }

    @Test
    void logout_WithInvalidHeader_NoActionTaken() {
        when(request.getHeader("Authorization")).thenReturn(null);

        logoutService.logout(request, response, authentication);

        verifyNoInteractions(tokenRepository);
    }
}

