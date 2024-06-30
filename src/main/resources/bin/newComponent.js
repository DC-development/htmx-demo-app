import fs from "fs";

import {createInterface} from "readline";

const myArgs = process.argv.slice(2)

myArgs.forEach(function (val, index, array) {
  console.log(index + ': ' + val)
})

let compName = "";
let useScript = false;

const rl = createInterface({
  input: process.stdin,
  output: process.stdout
});

const question1 = () => {
  return new Promise((resolve, reject) => {
    rl.question('What is the name of your component? ', (answer) => {
      compName = answer;
      resolve()
    })
  })
}

const question2 = () => {
  return new Promise((resolve, reject) => {
    rl.question('Are you going to use a script (J/N)? ', (answer) => {
      useScript = answer === "J"
      resolve()
    })
  })
}

const generateStuff = (name, useScript, path = "./html") => {

  fs.mkdir(`${path}/${name}`, (error) => {
    if(!!useScript) {
      fs.writeFile(
        `${path}/${name}/${name}.ts`,
        `export const ${name} = () => console.log('hhuhu! ${name}')`,
        (err,res) => {
          res = err ? err : `created ${name}.ts`
          console.log(res)
        });
    }
    fs.writeFile(
      `${path}/${name}/${name}.html`,
      `<b> ${name} seem to work!</b>`,
      (err,res) => {
        res = err ? err : `created ${name}.html`
        console.log(res)
      });

    fs.writeFile(
      `${path}/${name}/_${name}.scss`,
      `/** Generated SCSS for component ${name} */`,
      (err,res) => {
        res = err ? err : `created ${name}.scss`
        console.log(res)
      });
  })
}

const main = async () => {
  await question1()
  await question2()
  rl.close()
  console.log(compName, useScript, myArgs[0])
  generateStuff(compName, useScript, myArgs[0])
}

main()
