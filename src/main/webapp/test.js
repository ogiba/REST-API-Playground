/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.addEventListener("DOMContentLoaded", function () {

});

const colorPicker = document.querySelector("#colorPicker");
let inputHeight = document.getElementById("inputHeight");
let inputWidth = document.getElementById("inputWidth");
const sizePicker = document.querySelector("#sizePicker");
const table = document.querySelector("#pixelCanvas");
const rows = parseInt(inputHeight.value, 10);
const cols = parseInt(inputWidth.value, 10);

// Function Making grid
function makeGrid() {
    // Retrive the values of the input elements.
    const rows = parseInt(inputHeight.value, 10);
    const cols = parseInt(inputWidth.value, 10);
    const newGrid = document.createElement('table');
    const canvas = document.querySelector('#pixelCanvas');
    canvas.appendChild(newGrid);
    for (let i = 0; i < rows; i++) {
        const newRow = document.createElement("tr");
        //creates a row
        for (let j = 0; j < cols; j++) {
            //creates cells
            const newCell = document.createElement("td");
            newRow.appendChild(newCell);
        }
        canvas.appendChild(newRow);
    }
}


sizePicker.addEventListener('number', function (prev) {
    prev.preventDefault();
    makeGrid();
});

function myFunc(par) {
    const par = document.createElement('p');
    document.appendChild('p');
}

sizePicker.addEventListener("submit", function (e) {
    e.preventDefault();
    table.innerHTML = "";
    makeGrid();
});

colorPicker.addEventListener("change", function () {
    alert(colorPicker.value);
});
