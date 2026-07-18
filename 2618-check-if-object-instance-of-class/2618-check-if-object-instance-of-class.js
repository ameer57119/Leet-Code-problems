/**
 * @param {any} obj
 * @param {any} classFunction
 * @return {boolean}
 */
var checkIfInstanceOf = function(obj, classFunction) {
    // If the class function is null, undefined, or doesn't have a prototype, it's invalid
    if (classFunction === null || classFunction === undefined || !classFunction.prototype) {
        return false;
    }

    // Handle null or undefined objects early since they don't have prototypes
    if (obj === null || obj === undefined) {
        return false;
    }

    // Convert primitives to their object wrappers (e.g., 5 becomes Number(5))
    // so we can access their prototype chain cleanly
    let currProto = Object.getPrototypeOf(Object(obj));

    while (currProto !== null) {
        if (currProto === classFunction.prototype) {
            return true;
        }
        currProto = Object.getPrototypeOf(currProto);
    }

    return false;
};