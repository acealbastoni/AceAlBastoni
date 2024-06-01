//██████████████████████████████████████████████████████████████████████████████████████████████████████████████████
//### Get Req


fetch('https://script.google.com/macros/s/AKfycbzH1jCTWWh_zO7dKbFjXd0-It4WV-moWDjv5myQiNr5FekUSvzFBPXSk8D5zw2_COHJ/exec')
  .then(response => response.json())
  .then(data => {
    // Alert the URL fetched from the API
    //alert("New is:" + data.url);
    //alert(JSON.stringify(data.url))
    localStorage.setItem('NEXT_URL', JSON.stringify(data.url)?JSON.stringify(data.url):'https://www.linkedin.com/feed/');
  })
  .catch(error => {
    alert(error); // Alerting the error if any
    console.error('Error fetching data:', error);
  });



//██████████████████████████████████████████████████████████████████████████████████████████████████████████████████
// //### Post Req

// fetch('https://script.google.com/macros/s/AKfycbzH1jCTWWh_zO7dKbFjXd0-It4WV-moWDjv5myQiNr5FekUSvzFBPXSk8D5zw2_COHJ/exec', {
//   method: 'POST',
//   body: JSON.stringify(
//     {
//       "action": "updateCellValue",
//       "index": 9,
//       "category": "example"
//     }
//   )
// })
//   .then(response => response.json())
//   .then(data => {
//     // Alert the URL fetched from the API
//     alert("New is a post :" + data.url);
//     localStorage.setItem('NEXT_URL', data.url);

//   })
//   .catch(error => {
//     alert(error); // Alerting the error if any
//     console.error('Error fetching data:', error);
//   });

//---------------------------------------------------------------------------
// {
//   "action":"updateCellValue",//"getRandomUrl",
//   "index": 9,
//   "category": "example" // Example category value
// }

