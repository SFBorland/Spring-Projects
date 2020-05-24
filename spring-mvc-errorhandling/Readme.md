
Note: Look like they also overwrote or extended RestTemplate..like exchange() etc  

Making this project to do things like:
```
@ExceptionHandler({OCCResponseException.class})

    @Telemetry
    
    protected Response handleOCCResponseException(OCCResponseException e, WebRequest webRequest, HttpServletResponse httpServletResponse) {
    
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        
        Response response = responseUtil.getBaseErrorResponse(webRequest, OCC_STATUS_EXCEPTION_CODE, e.getMessage());
        
        response.setStatus(e.getStatusCode());
        
        response.getError().setMoreInfo(e.getMoreInfo());
        
        log.error("OCC error: {}, {}, {}", e.getMessage(), e.getStatusCode(), e.getMoreInfo());
        
        return response;
    }
```

Heres the ```OCCResponseException.class```:
```
@Data
public class OCCResponseException extends RuntimeException {

    private final String moreInfo;
    private final int statusCode;

    public OCCResponseException(String message, String moreInfo, int statusCode) {
        super(message);
        this.moreInfo = moreInfo;
        this.statusCode = statusCode;
    }
}
```
More detail:
```
catch (HttpClientErrorException e) {
            throw handleException(e, url, method.name(), operation, start, context);
        }

/** utility method to handle client error exceptions */
    private OCCResponseException handleException(HttpClientErrorException e, String url, String method, String operation, long start, Map<String, String> params) {
        long elapsed = System.currentTimeMillis() - start;
        telemetryHelper.publishRequestEvent(method, operation, RESPONSE_APP_ID, url, e.getStatusCode().value(), elapsed, params);
        return new OCCResponseException(
                String.format("failed to get REST result for uri=%s, method=%s, params=%s", url, method, params),
                String.format("response: %s", cleanResponse(e)),
                e.getStatusCode().value());
    }
```
