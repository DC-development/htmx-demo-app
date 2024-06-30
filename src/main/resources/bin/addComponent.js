const fs = require("fs");

/*
const readline = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
});*/

// print process.argv
const myArgs = process.argv.slice(2)

myArgs.forEach(function (val, index, array) {
  console.log(index + ': ' + val)
})

/*
readline.question('What is the name of your component? ', (name) => {
  console.log(`Generating files for: ${name}!`, myArgs)
  if(myArgs.length === 2) {
    generateStuff(name)
  } else {
    console.log('no name for component error, exiting!')
    process.exit()
  }

  readline.close();
});*/

const generateStuff = (name, path) => {

  fs.mkdir(`${path}/${name}`, (error) => {

    fs.writeFile(
      `${path}/${name}/${name}.ts`,
      `export const ${name} = () => console.log('hhuhu! ${name}')`,
      (err,res) => {
        res = err ? err : `created ${name}.ts`
        console.log(res)
      });

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

if(myArgs.length === 2) {
  generateStuff(myArgs[0], myArgs[1])
} else {
  console.log(`wrong number of arguments expected 2, but found ${myArgs.length}`)
}
