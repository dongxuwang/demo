Mobile Phone Booking Service
============================

### Assumption:
A mock api has been built for dummy request data from fonoapi because which has been down

### Usage:
Could also check `src/test/kotlin/example/BookServiceTest.kt` for service examples

```kotlin
// 1. get service
val bookingService = BookingService()
// 2. book phone
val phoneResult = bookingService.book("Samsung Galaxy S9", "martin")
if (phoneResult.isSuccess) {
    val phone = phoneResult.getOrThrow()
    // 3. do testing on the phone
    // ...
    // 4. and then return it
    bookingService.restore(phone.name)
}
```
