package com.ksh.providers;

import com.ksh.entities.User;
import com.ksh.repositories.UserRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Inject
    private UserRepository userRepository;

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create(emitter -> {
            System.out.println("Authentication...");
            System.out.println("Username : "+authenticationRequest.getIdentity().toString());
            System.out.println("Password : "+authenticationRequest.getSecret().toString());
//            User user = userRepository.findByUsernameAndPassword(authenticationRequest.getIdentity().toString(), authenticationRequest.getSecret().toString());
            if (authenticationRequest.getIdentity().equals("kshitij") && authenticationRequest.getSecret().equals("kshitij")) {
                emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
                emitter.complete();
            } else {
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
