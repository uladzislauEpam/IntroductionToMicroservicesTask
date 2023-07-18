- ResourceController.get value for title:\
object.put('title', Arrays.toString(resource.getAudioBytes())) looks strange\
**Refactored controllers**
- Missing validation for delete method\
**Added validation method**
- Method getAll() is not a good idea in Resource.\
What happens if your DB contains 1000 records and someone is trying to getAll()?\
Think about limiting the records you give away.\
**Implemented paging**
- Song metadata should contain more information than title and resourceId.\
**Added fields**
- It's better to move 'localhost:8090|api|song' to application.yml\
**Moved to application.properties**
- I suggest to return Song from SongController.get() not a map.\
**Refactored controllers**
- Could you check and update your application according to the following requirement:\
'400 Validation failed or request body is invalid MP3'\
**Added 400 response for invalid requests**