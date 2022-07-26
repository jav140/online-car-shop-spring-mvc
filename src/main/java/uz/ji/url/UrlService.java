package uz.ji.url;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import uz.ji.url.dto.UrlCreateDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UrlService {


    private final UrlDao urlDao;

    public UrlService(UrlDao urlDao) {
        this.urlDao = urlDao;
    }

    public List<UrlDomain> findAll() {
        return urlDao.getAll();
    }

    public void generate(UrlCreateDto dto) {

        LocalDateTime validTill = Objects.nonNull(dto.getValidTill())
                ? LocalDateTime.parse(dto.getValidTill()) :LocalDateTime.now().plusMinutes(10);
        String shortenedUrl = DigestUtils.md5DigestAsHex(dto.getOriginalUrl().getBytes());

        UrlDomain urlDomain = UrlDomain.builder()
                .validTill(Timestamp.valueOf(validTill))
                .originalUrl(dto.getOriginalUrl())
                .description(dto.getDescription())
                .shortenedUrl(shortenedUrl)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        urlDao.save(urlDomain);
    }

    public UrlDomain findByShortenedUrl(String shortenedUrl) {
        return urlDao.findByShortenedUrl(shortenedUrl);
    }
}
