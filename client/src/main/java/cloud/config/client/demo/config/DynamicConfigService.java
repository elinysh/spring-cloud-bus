package cloud.config.client.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope // 여기가 다르다.
// config 저장소에서 설정 파일을 변경했을 때 변경사항을 갱신할 수 있도록 설정하는 것이다.
public class DynamicConfigService {

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
