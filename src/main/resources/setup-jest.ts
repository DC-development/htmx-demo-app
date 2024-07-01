import 'jest-preset-angular/setup-jest';
const crypto = require('crypto');

Object.defineProperty(globalThis, 'crypto', {
  value: {
    randomUUID: (arr) => crypto.randomBytes(arr?.length || 0),
    getRandomValues: (arr) => crypto.randomBytes(arr.length)
  }
});
