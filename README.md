# spring-cache-resolver-hazelcast
Prefix Based Cache Resolver Implementation

Uses user defined prefix to enrich the cache name, so different instances of same application can create different caches, if needed.

## Usage
1. Run `com.example.cachedemoserver.CacheDemoServerApplication`to start a Hazelcast node.
2. Run `com.example.cachedemo.CacheDemoApplication` to start Hazelcast Client. This automatically calls some `Cacaheable` annottated method & print the result. For cache prefix, set `hazelcast.cache.prefix` parameter, to `application.properties` or from command line like `-Dhazelcast.cache.prefix`. (currently, `region-1` is used for cache prefix.)
3. Run `com.example.cachedemo.CacheDemoApplication` with setting a different prefix. You'll see that in the first method call, it do the method processing, because it's using a different cache.
4. If you run multiple `CacheDemoApplication` with same `hazelcast.cache.prefix`, then all calls will return cached value, other than very first call on first running instance with same prefix.
