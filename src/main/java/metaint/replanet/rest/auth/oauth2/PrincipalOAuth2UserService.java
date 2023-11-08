//package metaint.replanet.rest.auth.oauth2;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import metaint.replanet.rest.auth.repository.MemberRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
//
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        log.info("getAttributes : {}", oAuth2User.getAttributes());
//
//        OAuth2UserInfo oAuth2UserInfo = null;
//
//        String provider = userRequest.getClientRegistration().getRegistrationId();
//
//        if(provider.equals("google")) {
//            log.info("구글 로그인 요청");
//            oAuth2UserInfo = new GoogleUserInfo( oAuth2User.getAttributes() );
//        } else if(provider.equals("kakao")) {
//            log.info("카카오 로그인 요청");
//            oAuth2UserInfo = new KakaoUserInfo( (Map)oAuth2User.getAttributes() );
//        } else if(provider.equals("naver")) {
//            log.info("네이버 로그인 요청");
//            oAuth2UserInfo = new NaverUserInfo( (Map)oAuth2User.getAttributes().get("response") );
//        } else if(provider.equals("facebook")) {
//            log.info("페이스북 로그인 요청");
//            oAuth2UserInfo = new FacebookUserInfo( oAuth2User.getAttributes() );
//        }
//
//        String providerId = oAuth2UserInfo.getProviderId();
//        String email = oAuth2UserInfo.getEmail();
//        String loginId = provider + "_" + providerId;
//        String nickname = oAuth2UserInfo.getName();
//
//
//        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
//        User user = null;
//
//        if(optionalUser.isEmpty()) {
//            user = User.builder()
//                    .loginId(loginId)
//                    .nickname(nickname)
//                    .provider(provider)
//                    .providerId(providerId)
//                    .role(UserRole.USER)
//                    .build();
//            userRepository.save(user);
//        } else {
//            user = optionalUser.get();
//        }
//
//        return new PrincipalDetails(user, oAuth2User.getAttributes());
//    }
//}