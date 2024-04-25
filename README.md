Mobile Phone Booking Service
============================

### Usage:

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
    bookingService.restore(phone.id)
}
```
