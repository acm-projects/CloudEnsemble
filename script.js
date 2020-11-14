
export default function Script(){
const AudioContext = window.AudioContext || window.webkitAudioContext;
const audioCtx = new AudioContext();

const play = document.getElementById('start');
const test = document.getElementById("test");
const volumeElement = document.getElementById('volume-range');

var sources = [];
var gainNodes = [];
var convolverNodes = [];
var waveShaperNodes = [];
var pannerNodes = [];
const gainNode = audioCtx.createGain();

const clips = document.querySelectorAll('audio');

clips.forEach(function(currentClip, currentIndex) {
  var newButton = document.createElement("BUTTON");
  newButton.innerText = "Add clip " + (currentIndex+1) + " to rack";
  document.body.appendChild(newButton);

  var source = audioCtx.createMediaElementSource(currentClip);
  source.connect(gainNode).connect(audioCtx.destination);
  sources.push(source);

  newButton.addEventListener('click', function() {
    if (newButton.classList.contains("racked-button")) {
      newButton.classList.remove("racked-button");
      currentClip.classList.remove("racked");
    } else {
      newButton.classList.add("racked-button");
      currentClip.classList.add("racked");
    }
  })
})

/*volumeElement.addEventListener('input', function() {
  document.getElementById("volumep").innerHTML = this.value;
  gainNode.gain.value = this.value / 100 * 2;
});

play.addEventListener('click', function() {
  var clipsOnRack = document.querySelectorAll(".racked");
  console.log(clipsOnRack);
  clipsOnRack.forEach(function(currentClip) {
    currentClip.play();
  });
});

test.addEventListener('click', function() {
  var audioElement = document.getElementById('ms');
  var source = audioCtx.createMediaElementSource(audioElement);
  source.connect(gainNode).connect(audioCtx.destination);
  audioElement.play();
});*/
}