package com.example.project;

import java.net.URI;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

@SuppressWarnings("PMD")
public class ProjectResponseEntity<T> extends HttpEntity<T> {

    private Object status;

    public ProjectResponseEntity(HttpStatus status) {
        this((T) null, (MultiValueMap)null, (HttpStatus)status);
    }

    public ProjectResponseEntity(@Nullable T body, HttpStatus status) {
        this(body, (MultiValueMap)null, (HttpStatus)status);
    }

    public ProjectResponseEntity(MultiValueMap<String, String> headers, HttpStatus status) {
        this((T) null, headers, (HttpStatus)status);
    }

    /**
     * Atlas ResponseEntity constructor.
     * @param body Response body object
     * @param headers Response headers
     * @param status Http status
     */
    public ProjectResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers);
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status;
    }

    private ProjectResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, Object status) {
        super(body, headers);
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status;
    }

    public HttpStatus getStatusCode() {
        return this.status instanceof HttpStatus ? (HttpStatus)this.status : HttpStatus.valueOf((Integer)this.status);
    }

    public int getStatusCodeValue() {
        return this.status instanceof HttpStatus ? ((HttpStatus)this.status).value() : (Integer)this.status;
    }

    /**
     * Equals method.
     * @param other Incoming object
     * @return boolean
     */
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        } else if (!super.equals(other)) {
            return false;
        } else {
            ProjectResponseEntity<?> otherEntity = (ProjectResponseEntity)other;
            return ObjectUtils.nullSafeEquals(this.status, otherEntity.status);
        }
    }

    public int hashCode() {
        return super.hashCode() * 29 + ObjectUtils.nullSafeHashCode(this.status);
    }

    /**
     * ToString method.
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder("<");
        builder.append(this.status.toString());
        if (this.status instanceof HttpStatus) {
            builder.append(' ');
            builder.append(((HttpStatus)this.status).getReasonPhrase());
        }

        builder.append(',');
        T body = this.getBody();
        HttpHeaders headers = this.getHeaders();
        if (body != null) {
            builder.append(body);
            builder.append(',');
        }

        builder.append(headers);
        builder.append('>');
        return builder.toString();
    }

    public static ProjectResponseEntity.BodyBuilder status(HttpStatus status) {
        Assert.notNull(status, "HttpStatus must not be null");
        return new ProjectResponseEntity.DefaultBuilder(status);
    }

    public static ProjectResponseEntity.BodyBuilder status(int status) {
        return new ProjectResponseEntity.DefaultBuilder(status);
    }

    public static <T> ProjectResponseEntity<T> of(Optional<T> body) {
        Assert.notNull(body, "Body must not be null");
        return (ProjectResponseEntity)body.map(ProjectResponseEntity::ok).orElse(notFound().build());
    }

    public static ProjectResponseEntity.BodyBuilder ok() {
        return status(HttpStatus.OK);
    }

    public static <T> ProjectResponseEntity<T> ok(T body) {
        ProjectResponseEntity.BodyBuilder builder = ok();
        return builder.body(body);
    }

    public static ProjectResponseEntity.BodyBuilder created(URI location) {
        ProjectResponseEntity.BodyBuilder builder = status(HttpStatus.CREATED);
        return (ProjectResponseEntity.BodyBuilder)builder.location(location);
    }

    public static ProjectResponseEntity.BodyBuilder accepted() {
        return status(HttpStatus.ACCEPTED);
    }

    public static ProjectResponseEntity.HeadersBuilder<?> noContent() {
        return status(HttpStatus.NO_CONTENT);
    }

    public static ProjectResponseEntity.BodyBuilder badRequest() {
        return status(HttpStatus.BAD_REQUEST);
    }

    public static ProjectResponseEntity.HeadersBuilder<?> notFound() {
        return status(HttpStatus.NOT_FOUND);
    }

    public static ProjectResponseEntity.BodyBuilder unprocessableEntity() {
        return status(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static class DefaultBuilder implements ProjectResponseEntity.BodyBuilder {
        private final Object statusCode;
        private final HttpHeaders headers = new HttpHeaders();

        public DefaultBuilder(Object statusCode) {
            this.statusCode = statusCode;
        }

        public ProjectResponseEntity.BodyBuilder header(String headerName, String... headerValues) {
            String[] var3 = headerValues;
            int var4 = headerValues.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String headerValue = var3[var5];
                this.headers.add(headerName, headerValue);
            }

            return this;
        }

        public ProjectResponseEntity.BodyBuilder headers(@Nullable HttpHeaders headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }

            return this;
        }

        public ProjectResponseEntity.BodyBuilder allow(HttpMethod... allowedMethods) {
            this.headers.setAllow(new LinkedHashSet(Arrays.asList(allowedMethods)));
            return this;
        }

        public ProjectResponseEntity.BodyBuilder contentLength(long contentLength) {
            this.headers.setContentLength(contentLength);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder contentType(MediaType contentType) {
            this.headers.setContentType(contentType);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder restartRowId(String restartRowId) {
            this.headers.set("restartRowId", restartRowId);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder eeTag(String etag) {
            if (!etag.startsWith("\"") && !etag.startsWith("W/\"")) {
                etag = "\"" + etag;
            }

            if (!etag.endsWith("\"")) {
                etag = etag + "\"";
            }

            this.headers.setETag(etag);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder lastModified(ZonedDateTime date) {
            this.headers.setLastModified(date);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder lastModified(Instant date) {
            this.headers.setLastModified(date);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder lastModified(long date) {
            this.headers.setLastModified(date);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder location(URI location) {
            this.headers.setLocation(location);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder cacheControl(CacheControl cacheControl) {
            this.headers.setCacheControl(cacheControl);
            return this;
        }

        public ProjectResponseEntity.BodyBuilder varyBy(String... requestHeaders) {
            this.headers.setVary(Arrays.asList(requestHeaders));
            return this;
        }

        public <T> ProjectResponseEntity<T> build() {
            return this.body((T)null);
        }

        public <T> ProjectResponseEntity<T> body(@Nullable T body) {
            return new ProjectResponseEntity(body, this.headers, this.statusCode);
        }
    }

    public interface BodyBuilder extends ProjectResponseEntity.HeadersBuilder<ProjectResponseEntity.BodyBuilder> {
        ProjectResponseEntity.BodyBuilder contentLength(long var1);

        ProjectResponseEntity.BodyBuilder contentType(MediaType var1);

        <T> ProjectResponseEntity<T> body(@Nullable T var1);
    }

    public interface HeadersBuilder<B extends ProjectResponseEntity.HeadersBuilder<B>> {
        B header(String var1, String... var2);

        B headers(@Nullable HttpHeaders var1);

        B allow(HttpMethod... var1);

        B eeTag(String var1);

        B lastModified(ZonedDateTime var1);

        B lastModified(Instant var1);

        B lastModified(long var1);

        B location(URI var1);

        B cacheControl(CacheControl var1);

        B varyBy(String... var1);

        B restartRowId(String var1);

        <T> ProjectResponseEntity<T> build();
    }

}