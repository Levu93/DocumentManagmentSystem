/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function clearContents(element) {
    element.value = '';
}

function backContents(element) {
    if (element.value == '') {
        element.value = 'Process is for...';
    }
}

