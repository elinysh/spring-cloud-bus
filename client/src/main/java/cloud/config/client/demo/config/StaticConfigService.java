package cloud.config.client.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
// 하나는 설정값이 변경되더라도 다시 빌드, 배포하지 않아도 변경된 값을 보여준다.
public class StaticConfigService {

	@Value("${sehyeong.profile}")
	private String profile;
	@Value("${sehyeong.comment}")
	private String comment;

	public Map<String, String> getConfig() {
		Map<String, String> map = new HashMap<>();
		map.put("profile", profile);
		map.put("comment", comment);
		return map;
	}
}
